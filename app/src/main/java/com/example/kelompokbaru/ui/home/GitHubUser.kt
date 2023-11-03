import android.os.Parcel
import android.os.Parcelable

data class GitHubUser (
    val login: String,
    val name: String,
    val followers: Int,
    val following: Int,
    val avatar_url: String,
    val starred_url: String,
    val location: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(name)
        parcel.writeInt(followers)
        parcel.writeInt(following)
        parcel.writeString(avatar_url)
        parcel.writeString(starred_url)
        parcel.writeString(location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitHubUser> {
        override fun createFromParcel(parcel: Parcel): GitHubUser {
            return GitHubUser(parcel)
        }

        override fun newArray(size: Int): Array<GitHubUser?> {
            return arrayOfNulls(size)
        }
    }
}
