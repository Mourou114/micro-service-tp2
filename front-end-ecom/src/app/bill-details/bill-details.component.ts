import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-bill-details',
  standalone: false,
  templateUrl: './bill-details.component.html',
  styleUrl: './bill-details.component.css'
})
export class BillDetailsComponent implements OnInit{

  public billDetails : any;
  billId!:number;
  constructor(private http:HttpClient, private router: Router, private route:ActivatedRoute) {
    this.billId=route.snapshot.params['billId'];
  }
  ngOnInit(): void {
    this.http.get("http://localhost:8383/billing-service/api/bills/"+this.billId).subscribe({
      next :(data)=>{
        this.billDetails=data;
      },
      error :(err)=>{}
    });
  }

}

