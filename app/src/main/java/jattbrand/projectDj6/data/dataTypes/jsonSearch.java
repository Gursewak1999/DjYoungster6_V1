package jattbrand.projectDj6.data.dataTypes;

import java.util.ArrayList;


public class jsonSearch {
    private Cursor CursorObject;

    private Context ContextObject;
    public ArrayList<Object> results = new ArrayList<Object>();
    private FindMoreOnGoogle FindMoreOnGoogleObject;


    public Cursor getCursor() {
        return CursorObject;

    }


    public Context getContext() {
        return ContextObject;
    }


    public FindMoreOnGoogle getFindMoreOnGoogle() {


        return FindMoreOnGoogleObject;
    }


    public void setCursor(Cursor cursorObject) {

        this.CursorObject = cursorObject;
    }

    public void setContext(Context contextObject) {


        this.ContextObject = contextObject;
    }

    public void setFindMoreOnGoogle(FindMoreOnGoogle findMoreOnGoogleObject) {

        this.FindMoreOnGoogleObject = findMoreOnGoogleObject;
    }


    class FindMoreOnGoogle {


        private String url;


        public String getUrl() {
            return url;
        }


        public void setUrl(String url) {
            this.url = url;
        }
    }

    class Context {
        private String title;
        private String total_results;


        public String getTitle() {
            return title;
        }

        public String getTotal_results() {
            return total_results;
        }


        public void setTitle(String title) {
            this.title = title;
        }

        public void setTotal_results(String total_results) {
            this.total_results = total_results;

        }
    }

    class Cursor {
        private float currentPageIndex;
        private String estimatedResultCount;

        private String moreResultsUrl;


        private String resultCount;


        private String searchResultTime;

        ArrayList<Object> pages = new ArrayList<Object>();


        public float getCurrentPageIndex() {
            return currentPageIndex;
        }

        public String getEstimatedResultCount() {
            return estimatedResultCount;
        }

        public String getMoreResultsUrl() {
            return moreResultsUrl;
        }

        public String getResultCount() {
            return resultCount;
        }

        public String getSearchResultTime() {
            return searchResultTime;
        }


        public void setCurrentPageIndex(float currentPageIndex) {
            this.currentPageIndex = currentPageIndex;
        }

        public void setEstimatedResultCount(String estimatedResultCount) {
            this.estimatedResultCount = estimatedResultCount;
        }

        public void setMoreResultsUrl(String moreResultsUrl) {
            this.moreResultsUrl = moreResultsUrl;
        }

        public void setResultCount(String resultCount) {
            this.resultCount = resultCount;
        }

        public void setSearchResultTime(String searchResultTime) {
            this.searchResultTime = searchResultTime;
        }
    }
}