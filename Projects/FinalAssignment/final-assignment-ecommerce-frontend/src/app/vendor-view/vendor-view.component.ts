import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Constants } from 'src/config/constants';
import { FailModalComponent } from '../fail-modal/fail-modal.component';
import { ProductOrder } from '../model/order-model';
import { AuthenticationService } from '../service/authentication-service';
import { PagerService } from '../service/pager-service';
import { VendorEditComponent } from '../vendor-edit/vendor-edit.component';

@Component({
  selector: 'app-vendor-view',
  templateUrl: './vendor-view.component.html',
  styleUrls: ['./vendor-view.component.css']
})
export class VendorViewComponent implements OnInit{
  retrievedImage : any;
  name: any;
  username:any;
  productOrders: ProductOrder[] = [];
  brands:any [];
  category:any[];
 // array of all items to be paged
 private allItems: ProductOrder[];

 // pager object
 pager: any = {};

 // paged items
 pagedItems: ProductOrder[];

  constructor (
    private httpClient : HttpClient,
    private router : Router,
    private pagerService: PagerService,
    private modalService: NgbModal,
    private authenticationService: AuthenticationService,
    private globalConstant: Constants
    
  ) {}

   // implementing search of items after autocomplete
   //for search option 
   options:any[]=[];
   optionsOfSet = new Set();
  ordersAfterSearch:any []; // creating array for search
  searchProducts(event : string) {
    console.log(event);
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
    if(this.authenticationService.isUserLoggedIn()) {
      if(this.authenticationService.getRole() === 'CUSTOMER') {
        this.authenticationService.logout();
        this.router.navigate(['login']);
      }
    }
    document.getElementById('footer').style.visibility = "hidden";
    this.productOrders=[];
    this.allItems = [];
    this.ordersAfterSearch = [];
    this.brands = [];
    this.category = [];
    this.getProducts();
    this.getUser();
    
  }

  getProducts() {
    this.httpClient.get<any>(this.globalConstant.DOMAIN+'ecommerce/products/'+this.authenticationService.getLoggedInUserName()).subscribe(
      response => {
        if (response) {this.hideloader();}

        for (var val of response.data) {
            val.productImage = 'data:image/jpeg;base64,' + val.productImage;
            let effectivePrice = (val.price - val.discount) ;
            let isDiscountZero = false;
            if(val.discount === 0) {
              isDiscountZero = true;
            }
            this.productOrders.push(new ProductOrder(val, 0,false,JSON.parse(JSON.stringify(Number(effectivePrice).toFixed(2))),isDiscountZero));
       //setting brand and category  for searching
       this.optionsOfSet.add(val.brand);
       this.optionsOfSet.add(val.category);
       this.optionsOfSet.add(val.productName);
       this.brands.push(val.brand);
       this.category.push(val.category);
          }
        for(var v of this.optionsOfSet) {
          this.options.push(v);
        }
        this.allItems = this.productOrders;

        // initialize to page 1
        this.setPage(1);
        },
        error => {
          if(error) {
            this.hideloader();
            const modalRef = this.modalService.open(FailModalComponent);
                modalRef.componentInstance.failMessage = 'Some error occured while loading the product. Try Again!!';
                modalRef.componentInstance.buttonMessage = 'Add More Products';
          }
        });
  }
  hideloader() {
    document.getElementById('loading').style.display = 'none';
    document.getElementById('footer').style.visibility = "visible";
    }
  getUser() {
      this.httpClient.get<any>(this.globalConstant.DOMAIN+'ecommerce/user/'+this.authenticationService.getLoggedInUserName()).subscribe(
      response => {
          this.name = response.data.name;
          this.retrievedImage = 'data:image/jpeg;base64,' + response.data.userImage;
        }
      )
  }
  setPage(page: number) {
    // get pager object from service
    this.pager = this.pagerService.getPager(this.allItems.length, page);

    // get current page of items
    this.pagedItems = this.allItems.slice(this.pager.startIndex, this.pager.endIndex + 1);
}

  onAdd(){
    this.router.navigate(['vendor/add']);
  }
  onEdit(order:ProductOrder) {
    const modalRef = this.modalService.open(VendorEditComponent, {size: 'lg', windowClass: 'modal-xl'});
    modalRef.componentInstance.editOrder = order.product;
    modalRef.componentInstance.event.subscribe((emmitedValue: any) => {
      if(emmitedValue.orderEdited) {
        this.ngOnInit();
      }
    })
  }
  onSales() {
    this.router.navigate(['vendor/sales']);
  }
  onLogout() {
    this.authenticationService.logout();
    this.router.navigate(['login']);
  }

  filterOrderByCategory:any [];
  changeByCategory : string = 'false';
  changingValueOfCategory(event : any) {
    this.changeByCategory = 'true';
    this.changeByBrand = 'false'; // to make sure other filter get deselected
    if(event.target.value === 'Filter By Category' || event.target.value === 'Reset Filter') {
      this.allItems = this.productOrders;
      this.setPage(1);
      return;
    }
    this.filterOrderByCategory = [];
    for(var values of this.productOrders) {
      if(values.product.category === event.target.value) {
        this.filterOrderByCategory.push(values);
      }
    }
    this.allItems = this.filterOrderByCategory;
    this.filterOrderByCategory = [];
    this.setPage(1);
  }

  changeByBrand : string = 'false';
  filterOrderByBrand:any [];
  changingValueOfBrands(event : any) {
    this.changeByBrand = 'true'; 
    this.changeByCategory = 'false'; // to make sure other filter get deselected
    if(event.target.value === 'Filter By Brand' || event.target.value === 'Reset Filter') {
      this.allItems = this.productOrders;
      this.setPage(1);
      return;
    }
    this.filterOrderByBrand = [];
    for(var values of this.productOrders) {
      if(values.product.brand === event.target.value) {
        this.filterOrderByBrand.push(values);
      }
    }
    this.allItems = this.filterOrderByBrand;
    this.filterOrderByBrand = [];
    this.setPage(1);
  }

}
