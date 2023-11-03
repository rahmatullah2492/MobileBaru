package com.example.kelompokbaru.ui.motion_layout


class ScreenItem(@JvmField var title: String, @JvmField var description: String, @JvmField var screenImg: Int) {

    // Properti-propertri kelas yang diinisialisasi dalam konstruktor


    // Getter untuk title
    fun getTitle(): String {
        return title
    }

    // Setter untuk title
    fun setTitle(title: String) {
        this.title = title
    }

    // Getter untuk description
    fun getDescription(): String {
        return description
    }

    // Setter untuk description
    fun setDescription(description: String) {
        this.description = description
    }

    // Getter untuk screenImg
    fun getScreenImg(): Int {
        return screenImg
    }

    // Setter untuk screenImg
    fun setScreenImg(screenImg: Int) {
        this.screenImg = screenImg
    }
}