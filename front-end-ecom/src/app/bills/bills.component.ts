import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-bills',
  standalone: false,
  templateUrl: './bills.component.html',
  styleUrl: './bills.component.css'
})
export class BillsComponent implements OnInit{

  public bills : any;
  customerId!:number;
  constructor(private http:HttpClient, private router: Router, private route:ActivatedRoute) {
    this.customerId=route.snapshot.params['customerId'];
  }
  ngOnInit(): void {
    this.http.get("http://localhost:8383/billing-service/api/bills/search/byCustomerId?projection=fullBill&customerId="+this.customerId).subscribe({
      next :(data)=>{
        this.bills=data;
      },
      error :(err)=>{}
    });
  }


  getBillDetails(b: any) {
    this.router.navigateByUrl("bill-details/"+b.id);
  }
}

