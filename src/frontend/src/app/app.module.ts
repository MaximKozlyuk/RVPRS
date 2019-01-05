import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { BooksComponent } from './books/books.component';
import { AboutLibComponent } from './about-lib/about-lib.component';
import { ReadersComponent } from './readers/readers.component';
import {RouterModule, Routes} from "@angular/router";
import { SearchBooksComponent } from './books/search-books/search-books.component';
import { BookUiComponent } from './books/book-ui/book-ui.component';

const appRouts: Routes = [
  { path: '', component: BooksComponent},
  { path: 'readers', component: ReadersComponent },
  { path: 'aboutLib', component: AboutLibComponent},
  { path: 'books', component: BooksComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BooksComponent,
    AboutLibComponent,
    ReadersComponent,
    SearchBooksComponent,
    BookUiComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRouts)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
