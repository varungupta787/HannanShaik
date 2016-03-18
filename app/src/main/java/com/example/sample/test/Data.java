package com.example.sample.test;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by sample on 16-03-2016.
 */
public class Data {
    private String name;
    private String phoneNumber;
    private String photoUrl;
    private String contactUrl;
    private String email;
    private ArrayList<ViewData> viewData = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public ViewData getViewDataInstance() {
        return new ViewData();
    }

    public ArrayList<ViewData> getViewData() {
        return viewData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    class ViewData {
        private String type;
        private String title;
        private String imageUrl;
        private String locationUrl;
        private String moreImageURl;
        private String content;
        private Bitmap bitmap;

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getMoreImageURl() {
            return moreImageURl;
        }

        public void setMoreImageURl(String moreImageURl) {
            this.moreImageURl = moreImageURl;
        }

        public String getLocationUrl() {
            return locationUrl;
        }

        public void setLocationUrl(String locationUrl) {
            this.locationUrl = locationUrl;
        }
    }
}
