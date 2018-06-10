export class Product {
    id: string;
    title: string;
    price: number;
    category: string;
    imageUrl: string;

    constructor(title: string, price: number, category: string, imageUrl: string) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }
}
