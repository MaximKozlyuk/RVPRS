import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  private books : number[] = new Array<number>(4);

  constructor() { }

  ngOnInit() {
  }

}
