package exampls.com.movieappstageone;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class Movie implements Parcelable {
    String posterImage;
    String realeseDate;
    String title;
    double VoteAverage;
    String overview;
    int id;

    public Movie() {
    }

    public Movie(String posterImage, String title, int id) {
        this.posterImage = posterImage;
        this.title = title;
        this.id = id;
    }

    public Movie(int id, String posterImage, String title, String realeseDate, double VoteAverage, String overview) {
        this.id = id;
        this.posterImage = posterImage;
        this.title = title;
        this.realeseDate = realeseDate;
        VoteAverage = VoteAverage;
        this.overview = overview;
    }

    protected Movie(Parcel in) {
        posterImage = in.readString();
        realeseDate = in.readString();
        title = in.readString();
        VoteAverage = in.readDouble();
        overview = in.readString();
        id = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPosterImage() {
        return posterImage;
    }

    public String getRealeseDate() {
        return realeseDate;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return VoteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterImage);
        dest.writeString(realeseDate);
        dest.writeString(title);
        dest.writeDouble(VoteAverage);
        dest.writeString(overview);
        dest.writeInt(id);
    }
}
