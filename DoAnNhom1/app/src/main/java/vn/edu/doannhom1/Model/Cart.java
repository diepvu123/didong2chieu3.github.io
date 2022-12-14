package vn.edu.doannhom1.Model;

public class Cart {
    String itemId,image,itemName,itemCapacity,uid,sellBy;
    int price,quantity,total;


    public Cart(String itemId, String image, String itemName, String itemCapacity, String uid, String sellBy, int price, int quantity, int total) {
        this.itemId = itemId;
        this.image = image;
        this.itemName = itemName;
        this.itemCapacity = itemCapacity;
        this.uid = uid;
        this.sellBy = sellBy;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Cart(){}

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCapacity() {
        return itemCapacity;
    }

    public void setItemCapacity(String itemCapacity) {
        this.itemCapacity = itemCapacity;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSellBy() {
        return sellBy;
    }

    public void setSellBy(String sellBy) {
        this.sellBy = sellBy;
    }
}
