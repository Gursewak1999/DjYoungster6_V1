package jattbrand.projectDj6.data.dataTypes;

public class jsonResult {
    private String cacheUrl;
    private String clicktrackUrl;
    private String content;
    private String contentNoFormatting;
    private String title;
    private String titleNoFormatting;
    private String formattedUrl;
    private String unescapedUrl;
    private String url;
    private String visibleUrl;
    private RichSnippet RichSnippetObject;

    public String getCacheUrl() {


        return cacheUrl;
    }

    public String getClicktrackUrl() {
        return clicktrackUrl;

    }

    public String getContent() {


        return content;
    }


    public String getContentNoFormatting() {
        return contentNoFormatting;


    }

    public String getTitle() {

        return title;
    }


    public String getTitleNoFormatting() {
        return titleNoFormatting;
    }

    public String getFormattedUrl() {


        return formattedUrl;
    }


    public String getUnescapedUrl() {

        return unescapedUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getVisibleUrl() {
        return visibleUrl;
    }

    public RichSnippet getRichSnippet() {
        return RichSnippetObject;
    }


    public void setCacheUrl(String cacheUrl) {
        this.cacheUrl = cacheUrl;
    }

    public void setClicktrackUrl(String clicktrackUrl) {
        this.clicktrackUrl = clicktrackUrl;
    }


    public void setContent(String content) {


        this.content = content;
    }


    public void setContentNoFormatting(String contentNoFormatting) {


        this.contentNoFormatting = contentNoFormatting;
    }


    public void setTitle(String title) {


        this.title = title;

    }

    public void setTitleNoFormatting(String titleNoFormatting) {

        this.titleNoFormatting = titleNoFormatting;
    }


    public void setFormattedUrl(String formattedUrl) {


        this.formattedUrl = formattedUrl;
    }


    public void setUnescapedUrl(String unescapedUrl) {
        this.unescapedUrl = unescapedUrl;
    }


    public void setUrl(String url) {


        this.url = url;

    }


    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;

    }

    public void setRichSnippet(RichSnippet richSnippetObject) {
        this.RichSnippetObject = richSnippetObject;
    }

    public class RichSnippet {
        CseImage CseImageObject;
        Metatags MetatagsObject;


        CseThumbnail CseThumbnailObject;



        public CseImage getCseImage() {

            return CseImageObject;
        }

        public Metatags getMetatags() {
            return MetatagsObject;
        }

        public CseThumbnail getCseThumbnail() {
            return CseThumbnailObject;
        }


        public void setCseImage(CseImage cseImageObject) {


            this.CseImageObject = cseImageObject;

        }


        public void setMetatags(Metatags metatagsObject) {

            this.MetatagsObject = metatagsObject;
        }


        public void setCseThumbnail(CseThumbnail cseThumbnailObject) {
            this.CseThumbnailObject = cseThumbnailObject;
        }
    }


    public class CseThumbnail {

        private String src;
        private String width;

        private String height;


        public String getSrc() {

            return src;
        }


        public String getWidth() {
            return width;
        }

        public String getHeight() {

            return height;
        }


        public void setSrc(String src) {
            this.src = src;
        }

        public void setWidth(String width) {

            this.width = width;
        }

        public void setHeight(String height) {

            this.height = height;
        }
    }

    public class Metatags {
        private String viewport;
        private String rating;
        private String ogTitle;
        private String language;
        private String google;
        private String ogDescription;
        private String fbAppId;
        private String ogType;



        public String getViewport() {
            return viewport;
        }

        public String getRating() {
            return rating;
        }

        public String getOgTitle() {
            return ogTitle;
        }

        public String getLanguage() {
            return language;
        }

        public String getGoogle() {
            return google;
        }

        public String getOgDescription() {
            return ogDescription;
        }

        public String getFbAppId() {
            return fbAppId;
        }

        public String getOgType() {
            return ogType;
        }


        public void setViewport(String viewport) {
            this.viewport = viewport;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public void setOgTitle(String ogTitle) {
            this.ogTitle = ogTitle;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public void setGoogle(String google) {
            this.google = google;
        }

        public void setOgDescription(String ogDescription) {
            this.ogDescription = ogDescription;
        }

        public void setFbAppId(String fbAppId) {
            this.fbAppId = fbAppId;
        }

        public void setOgType(String ogType) {
            this.ogType = ogType;
        }
    }

    public class CseImage {
        private String src;



        public String getSrc() {
            return src;
        }


        public void setSrc(String src) {
            this.src = src;
        }
    }
}