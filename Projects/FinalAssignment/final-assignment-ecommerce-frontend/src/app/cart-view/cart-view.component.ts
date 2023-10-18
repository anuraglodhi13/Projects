import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Constants } from 'src/config/constants';
import { FailModalComponent } from '../fail-modal/fail-modal.component';
import { BuyOrder, ProductOrder } from '../model/order-model';
import { AuthenticationService } from '../service/authentication-service';
import { SuccessModalComponent } from '../success-modal/success-modal.component';

@Component({
  selector: 'app-cart-view',
  templateUrl: './cart-view.component.html',
  styleUrls: ['./cart-view.component.css']
})
export class CartViewComponent implements OnInit{
  @Input() orders:ProductOrder[] = [];
  @Input() total:number;
  
  public event: EventEmitter<any> = new EventEmitter();

  buyOrder = new BuyOrder;
  buyOrders : ProductOrder[] = [];
  constructor(public activeModal: NgbActiveModal,
    public httpClient: HttpClient,
    public modalService: NgbModal,
    private authenticationService: AuthenticationService,
    private router: Router,
    private globalConstant: Constants) {
    
   }
  ngOnInit(): void {
    if(this.authenticationService.isUserLoggedIn()) {
      if(this.authenticationService.getRole() === 'VENDOR') {
        this.authenticationService.logout();
        this.router.navigate(['login']);
      }
    }
    }

   finishOrder(orders: ProductOrder[], total : number){
    this.buyOrders = JSON.parse(JSON.stringify(orders)); // to copy the array without reference
    for(var val of this.buyOrders) {
      val.product.productImage = val.product.productImage.split('base64,')[1];
    }
    this.buyOrder.orders = this.buyOrders;
    this.buyOrder.username = this.authenticationService.getLoggedInUserName();
    this.httpClient.post<any>(this.globalConstant.DOMAIN +'ecommerce/order',this.buyOrder).subscribe(
      response =>{
        if(response.status === 201) {
          this.activeModal.dismiss('Cross click');
          const modalRef = this.modalService.open(SuccessModalComponent);
          modalRef.componentInstance.buttonMessage = 'Back To Shopping';
          modalRef.componentInstance.successMessage = 'Your order has been created successfully.';
          this.event.emit({cartItemBought: true});
        }
      },
      error => {
        console.log(error);
        this.activeModal.dismiss('Cross click');
        const modalRef = this.modalService.open(FailModalComponent);
        modalRef.componentInstance.buttonMessage = 'Back To Shopping';
        modalRef.componentInstance.failMessage = 'Some error occured while adding your product. Try Again!!';
        this.event.emit({cartItemBought: true}); // if error occur reset the cart and other things
        return;
      }
    );
  }
}
