import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Manufacturers from '../Manufacturers/manufacturers';
import Categories from '../Categories/categories';
import Books from '../Products/ProductList/products';
import Header from '../Header/header';
import ProductAdd from '../Products/ProductAdd/productAdd';
import EShopService from "../../repository/eshopRepository";
import ProductEdit from "../Products/ProductEdit/productEdit";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      authors: [], //authors //surname
      books: [],
      // categories: [],
      // selectedProduct: {} //selectedBooks
    }
  }

  render() {
    return (
        <Router>
          <Header/>
          <main>
            <div className="container">
              {/*<Route path={"/manufacturers"} exact render={() => // /authors*/}
              {/*    <Manufacturers manufacturers={this.state.manufacturers}/>}/>*/}
              {/*<Route path={"/categories"} exact render={() =>*/}
              {/*    <Categories categories={this.state.categories}/>*/}
              {/*}/>*/}
              {/*<Route path={"/products/add"} exact render={() => // /books/add*/}
              {/*    <BookAdd categories={this.state.categories}*/}
              {/*                manufacturers={this.state.manufacturers}*/}
              {/*                onAddProduct={this.addProduct}/>}/>*/}
              {/*<Route path={"/products/edit/:id"} exact render={() => // /books/edit/:id*/}
              {/*    <ProductEdit categories={this.state.categories}*/}
              {/*                 manufacturers={this.state.manufacturers}*/}
              {/*                 onEditProduct={this.editProduct}*/}
              {/*                 product={this.state.selectedProduct}/>}/>*/}
              <Route path={"/books"} exact render={() =>
                  <Books books={this.state.books}
                            onDelete={this.deleteBook}
                            onEdit={this.getBook}/>}/>
              <Redirect to={"/books"}/>
            </div>
          </main>
        </Router>
    );
  }

  componentDidMount() {
    // this.loadManufacturers(); // this.loadAuthors();
    // this.loadCategories();
    this.loadBooks();
  }
  //
  // loadManufacturers = () => { // loadAuthors
  //   EShopService.fetchManufacturers() //fetchAuthors
  //       .then((data) => {
  //         this.setState({
  //           manufacturers: data.data //authors
  //         })
  //       });
  // }

  loadBooks = () => {
    EShopService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          })
        });
  }

  // loadCategories = () => {
  //   EShopService.fetchCategories()
  //       .then((data) => {
  //         this.setState({
  //           categories: data.data
  //         })
  //       });
  // }

  deleteBook = (id) => {
    EShopService.deleteBook(id)
        .then(() => {
          this.loadBooks();
        });
  }

  // addBook = (name, quantity, category, manufacturer) => { // author
  //   EShopService.addBook(name, quantity, category, manufacturer) // author
  //       .then(() => {
  //         this.loadBook();
  //       });
  // }

  getBook = (id) => {
    EShopService.getBook(id)
        .then((data) => {
          this.setState({
            selectedBook: data.data
          })
        })
  }

  // editBook = (id, name,  quantity, category, manufacturer) => { // author
  //   EShopService.editBook(id, name, quantity, category, manufacturer) // author
  //       .then(() => {
  //         this.loadBook();
  //       });
  // }
}

export default App;
