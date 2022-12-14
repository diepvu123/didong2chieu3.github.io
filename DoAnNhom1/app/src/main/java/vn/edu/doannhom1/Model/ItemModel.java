package vn.edu.doannhom1.Model;

public class ItemModel {
    String itemName,itemDescription,itemCapacity,image;
    String seller,itemId,category;
    int price;//rating;


    public ItemModel(String itemName, String itemDescription, String itemCapacity, String image, String seller, String itemId, String category, int price) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCapacity = itemCapacity;
        this.image = image;
        this.seller = seller;
        this.itemId = itemId;
        this.category = category;
        this.price = price;
        //this.rating = rating;
    }

    public ItemModel(){}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemCapacity() {
        return itemCapacity;
    }

    public void setItemCapacity(String itemCapacity) {
        this.itemCapacity = itemCapacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

//    public int getRating() {
//        return rating;
//    }

//    public void setRating(int rating) {
//        this.rating = rating;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
