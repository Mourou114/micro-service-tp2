import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-customers',
  standalone: false,
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{

  public customers : any;
  constructor(private http:HttpClient, private router: Router) {
  }
  ngOnInit(): void {
    this.http.get("http://localhost:8383/customer-service/api/customers").subscribe({
      next :(data)=>{
        this.customers=data;
      },
      error :(err)=>{}
    });
  }



  getBills(c: any) {
    this.router.navigateByUrl("/bills/"+c.id);
  }
}
