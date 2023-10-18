import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { Data, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ProductOrder } from '../model/order-model';
import { ProductFormModel } from '../model/vendor-add-models';
import { PagerService } from '../service/pager-service';
import { ProductService } from '../service/product-service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CartViewComponent } from '../cart-view/cart-view.component';
import { BuyNowViewComponent } from '../buy-now-view/buy-now-view.component';
import { AuthenticationService } from '../service/authentication-service';
import { FormControl } from '@angular/forms';
import { FailModalComponent } from '../fail-modal/fail-modal.component';
import { Constants } from 'src/config/constants';
@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrls: ['./customer-view.component.css'],
})
export class CustomerViewComponent implements OnInit {
  productOrders: ProductOrder[] = [];
  buyNowOrder: ProductOrder[] = [];
  cartOrders: ProductOrder[] = [];
  products: ProductFormModel[] = [];
  searchText: any;
  // for cart icon
  public count: number = 0;
  subscription: Subscription;

  userImage: any;
  name: any;
  username: any;
  productsAddedToCart: ProductFormModel[];

  // array of all items to be paged
  private allItems: ProductOrder[];

  // pager object
  pager: any = {};

  // paged items
  pagedItems: ProductOrder[];

  number: any;
  public event: EventEmitter<any> = new EventEmitter();
  myControl = new FormControl();
  constructor(
    private httpClient: HttpClient,
    private productService: ProductService,
    private pagerService: PagerService,
    private modalService: NgbModal,
    private authenticationService: AuthenticationService,
    private router: Router,
    private globalConstant: Constants
  ) {
    // for cart number of items increased 
    this.subscription = this.productService.getNumber().subscribe((number) => {
      this.number = number;
    });
  }

 

  // implementing search of items after autocomplete
   //for search option 
   options:any[]=[];
   optionsOfSet = new Set();
  ordersAfterSearch:any []; // creating array for search
  searchProducts(event : string) {
    if(event === undefined || event === null || event.length === 0) {
      this.allItems = this.productOrders;
    this.setPage(1);
    return;
    }
    this.ordersAfterSearch = [];
    for(var val of this.productOrders) { // searching product array based on brand category and product name
      if( (val.product.brand === event )|| (val.product.category === event) || (val.product.productName === event) ) {
        this.ordersAfterSearch.push(val);
      }
    }
    if(this.ordersAfterSearch === null || this.ordersAfterSearch.length === 0 || this.ordersAfterSearch === undefined) {
      // if not found any result show error 
      const modalRef = this.modalService.open(FailModalComponent);
      modalRef.componentInstance.buttonMessage = 'Back To Shopping';
      modalRef.componentInstance.failMessage = 'No result found for your search';
    }
    else {
      // paginate result
    this.allItems = this.ordersAfterSearch;
    this.setPage(1);
    }
  }

  ngOnInit(): void {
    

    if (this.authenticationService.isUserLoggedIn()) {
      if (this.authenticationService.getRole() === 'VENDOR') {
        this.authenticationService.logout();
        this.router.navigate(['login']);
      }
    }
    document.getElementById('footer').style.visibility = "hidden";
    this.count = 0;
    this.productService.sendNumber(0);
    this.cartOrders = [];
    this.productOrders = [];
    this.ordersAfterSearch = [];
    this.products = [];
    this.allItems = [];
    this.getProducts();
    this.getUser();
  }

  getUser() {
    this.httpClient
      .get<any>(this.globalConstant.DOMAIN +
        'ecommerce/user/' +
          this.authenticationService.getLoggedInUserName()
      )
      .subscribe((response) => {
        this.name = response.data.name;
        this.userImage = 'data:image/jpeg;base64,' + response.data.userImage;
      });
  }
  getProducts() {
    this.httpClient
      .get<any>(this.globalConstant.DOMAIN +'ecommerce/products')
      .subscribe((response) => {
        if (response) {
          this.hideloader();
        }
        for (var val of response.data) {
          val.productImage = 'data:image/jpeg;base64,' + val.productImage;
          const effectivePrice = (val.price) - (val.discount);
          let isDiscountZero = false;
          if (val.discount === 0) {
            isDiscountZero = true;
          }
          this.productOrders.push(
            new ProductOrder(val, 0, false,JSON.parse(JSON.stringify(Number(effectivePrice).toFixed(2))), isDiscountZero)
          );
          //setting brand category and product name for searching
          this.optionsOfSet.add(val.brand);
          this.optionsOfSet.add(val.category);
          this.optionsOfSet.add(val.productName);
        }
        //options in search box
        for(var v of this.optionsOfSet) {
          this.options.push(v);
        }
        document.getElementById('loading').style.display = 'none';
        this.allItems = this.productOrders;

        // initialize to page 1
        this.setPage(1);
      },
      error => {
        if(error) {
          this.hideloader();
          const modalRef = this.modalService.open(FailModalComponent);
              modalRef.componentInstance.failMessage = 'Some error occured while loading the product. Try Again!!';
              modalRef.componentInstance.buttonMessage = 'Back To Shopping';
        }
      });
  }
  hideloader() {
    document.getElementById('loading').style.display = 'none';
    document.getElementById('footer').style.visibility = "visible";
    }
  setPage(page: number) {
    // get pager object from service
    this.pager = this.pagerService.getPager(this.allItems.length, page);

    // get current page of items
    this.pagedItems = this.allItems.slice(
      this.pager.startIndex,
      this.pager.endIndex + 1
    );
  }

  increamentForCartIcon() {
    this.count += 1;
    return this.count;
  }
  decreamentForCartIcon() {
    this.count -= 1;
    return this.count;
  }

  addToCart(order: ProductOrder) {
    this.productService.sendNumber(this.increamentForCartIcon());

    // adding order to cartOrders
    // Cart Page Actions

    this.checkIfExist(order.product);
    this.cartOrders.push(order);
    this.productOrders.at(
      this.getProductIndex(order.product)
    ).currentProductSelected = true;
    this.productOrders.at(this.getProductIndex(order.product)).quantity++;
  }

  checkIfExist(productOrder: ProductFormModel) {
    const indexCartOrder = this.getProductIndexForCart(productOrder);
    if (indexCartOrder > -1) {
      this.cartOrders.splice(indexCartOrder, 1);
    }
  }

  // getProductIndex for Customer View Page
  getProductIndex(product: ProductFormModel): number {
    return this.productOrders.findIndex((value) => value.product === product);
  }

  //getProductIndex for Cart Products Page
  getProductIndexForCart(product: ProductFormModel): number {
    return this.cartOrders.findIndex((value) => value.product === product);
  }

  // this button will first find the index of product and increament it's quantity
  incrementButton(product: ProductFormModel, order: ProductOrder) {
    this.productService.sendNumber(this.increamentForCartIcon());

    // Product Page Actions
    const index = this.getProductIndex(product);
    this.productOrders.at(index).quantity++;
    if (this.productOrders.at(index).quantity <= 0) {
      this.productOrders.at(index).currentProductSelected = false;
    }

    // Cart Page Actions
    this.checkIfExist(product);
    this.cartOrders.push(order);
  }

  // this button will first find the index of product and decreament it's quantity
  decrementBUtton(product: ProductFormModel, order: ProductOrder) {
    this.productService.sendNumber(this.decreamentForCartIcon());
    const index = this.getProductIndex(product);
    this.productOrders.at(index).quantity--;
    if (this.productOrders.at(index).quantity <= 0) {
      // remove from cart
      this.productOrders.at(index).currentProductSelected = false;
      // Cart Page Actions
      const indexCartOrder = this.getProductIndexForCart(product);
      this.cartOrders.splice(indexCartOrder, 1);
    }
  }

  moveToCart() {
    let total = 0;
    for (var val of this.cartOrders) {
      total += val.quantity * Number(val.effectivePrice);
    }
    const modalRef = this.modalService.open(CartViewComponent);
    modalRef.componentInstance.orders = this.cartOrders;
    modalRef.componentInstance.total = Number(total).toFixed(2);

    modalRef.componentInstance.event.subscribe((emmitedValue: any) => {
      // to know cart has been checkout
      if (emmitedValue.cartItemBought) {
        this.ngOnInit();
      }
    });
  }

  buyNow(order: ProductOrder) {
    this.buyNowOrder = [];
    this.buyNowOrder.push(order);
    const total = this.buyNowOrder.at(0).effectivePrice;
    const modalRef = this.modalService.open(BuyNowViewComponent);
    modalRef.componentInstance.orders = this.buyNowOrder;
    modalRef.componentInstance.total = Number(total).toFixed(2);
  }

  onLogout() {
    this.authenticationService.logout();
    this.router.navigate(['login']);
  }
}
