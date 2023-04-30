import axios from '../custom-axios/axios';

const LibraryService = { //LibraryService
    fetchAuthors: () => {
        return axios.get("/author"); // /authors
    },
    // fetchCategories: () => {
    //     return axios.get("/categories");
    // },
    fetchBooks: () => {
        return axios.get("/books");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, quantity, category, author) => {
        return axios.post("/book/add", {
            "name" : name,
            "quantity" : quantity,
            "category" : category,
            "manufacturer" : author
        });
    },
    editBook: (id, name, quantity, category, author) => {
        return axios.put(`/books/edit/${id}`, {
            "name" : name,
            "quantity" : quantity,
            "category" : category,
            "manufacturer" : author
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    }
}

export default LibraryService;
