package com.loco.demo.DTO.JSON;

import java.util.Date;
import java.util.List;

import com.loco.demo.entity.Furniture;
import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.HotelRating;
import com.loco.demo.entity.Location;
import com.loco.demo.utils.Converters.SecureUser;

public class HotelDTO {
    private String id;
    private Date createAt;
    private String thumbnail;
    private String name;
    private String location;
    private Integer price;
    private Integer minPrice;
    private Integer maxPrice;
    private Byte minPeriod;
    private Byte mediumPeriod;
    private Byte maxPeriod;
    private Furniture furniture;
    private SecureUser seller;
    private List<String> imgList;
    private List<String> amenities;
    private List<String> safety;
    private HotelRating rating;
    private Integer capacity;
    private Integer type;
    private Byte status;
    private String description;
    private Location locationId;
    private String slug;

    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.createAt = hotel.getCreateAt();
        this.thumbnail = hotel.getThumbnail();
        this.name = hotel.getName();
        this.location = hotel.getLocation();
        this.price = hotel.getPrice();
        this.minPrice = hotel.getMinPrice();
        this.maxPrice = hotel.getMaxPrice();
        this.minPeriod = hotel.getMinPeriod();
        this.mediumPeriod = hotel.getMediumPeriod();
        this.maxPeriod = hotel.getMaxPeriod();
        this.furniture = hotel.getFurniture();
        this.seller = hotel.getSeller();
        this.imgList = hotel.getImgList();
        this.amenities = hotel.getAmenities();
        this.safety = hotel.getSafety();
        this.rating = hotel.getRating();
        this.capacity = hotel.getCapacity();
        this.type = hotel.getType();
        this.status = hotel.getStatus();
        this.description = hotel.getDescription();
        this.locationId = hotel.getLocationId();
        this.slug=hotel.getSlug();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Byte getMinPeriod() {
        return minPeriod;
    }

    public void setMinPeriod(Byte minPeriod) {
        this.minPeriod = minPeriod;
    }

    public Byte getMediumPeriod() {
        return mediumPeriod;
    }

    public void setMediumPeriod(Byte mediumPeriod) {
        this.mediumPeriod = mediumPeriod;
    }

    public Byte getMaxPeriod() {
        return maxPeriod;
    }

    public void setMaxPeriod(Byte maxPeriod) {
        this.maxPeriod = maxPeriod;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public SecureUser getSeller() {
        return seller;
    }

    public void setSeller(SecureUser seller) {
        this.seller = seller;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public List<String> getSafety() {
        return safety;
    }

    public void setSafety(List<String> safety) {
        this.safety = safety;
    }

    public HotelRating getRating() {
        return rating;
    }

    public void setRating(HotelRating rating) {
        this.rating = rating;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}