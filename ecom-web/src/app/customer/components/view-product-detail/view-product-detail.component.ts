import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-view-product-detail',
  templateUrl: './view-product-detail.component.html',
  styleUrls: ['./view-product-detail.component.scss']
})
export class ViewProductDetailComponent {

  productId:number=this.activatedRoute.snapshot.params['productId'];
 product:any;
 FAQS:any[]=[];
 reviews:any[]=[];

  constructor(private customerService:CustomerService,
    private snackBar:MatSnackBar,
    private activatedRoute:ActivatedRoute,
    private userStorageService:UserStorageService
  ){}
  ngOnInit(){
     this.getProductDetailById();
  }
  getProductDetailById(){
     this.customerService.getProductDetailById(this.productId).subscribe(res=>{
      this.product=res.productDto;
      this.product.processedImg='data:image/png;base64,'+ res.productDto.byteImg;
      this.FAQS=res.faqDtoList;
      res.reviewDtoList.forEach(element => {
        element.processedImg='data:image/png;base64,'+ element.returnedImg;
        this.reviews.push(element);
      });
      console.log(this.reviews);
     })
  }

  addToWishlist(){
    const wishlistDto={
      productId:this.productId,
      userId:this.userStorageService.getUserId()
    }
    this.customerService.addProductToWishlist(wishlistDto).subscribe(res=>{
      if(res.id !=null){
        this.snackBar.open('Product added to Wishlist successfully!','Close',{duration:5000});
      }else{
        this.snackBar.open("Already in wishlist",'ERROR',{duration:5000});
      }
    })
  }
  getStars(rating: number): number[] {
    return Array(rating).fill(0);
  }

}
