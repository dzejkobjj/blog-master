/**
 * Created by Dzejkob on 17.12.2017.
 */
$(document).ready(function () {
    var url = "http://localhost:8080/rest/article";
    var element_position = $('#scroll-to').offset().top;
    var loaded = false;
    var lastId = $(".article").last().attr("id") - 1;
        $(window).on('scroll', function () {
            if (window.pageYOffset > (element_position - $(window).height()) && loaded === false) {
                loaded = true;
                $.getJSON(url, {
                    id: lastId
                }).done(function (data) {
                    $("#scroll-to").remove();
                    var articleDiv = $("<div>").attr("class", "article").attr("id", data.id);
                    var imageDiv = $("<div>").attr("class", "article-image").appendTo(articleDiv);
                    var img = $("<img>").attr("src", data.imageUrl).appendTo(imageDiv);
                    var h1 = $("<h1>").appendTo(imageDiv);
                    var titleSpan = $("<span>").append(data.title).appendTo(h1);
                    var detailsDiv = $("<div>").attr("class", "row article-details").appendTo(articleDiv);
                    var authorDiv = $("<div>").attr("class", "col-sm").append("AUTOR:").append("<br/>").appendTo(detailsDiv);
                    var authorSpan = $("<span>").append(data.author).appendTo(authorDiv);
                    var commentsDiv = $("<div>").attr("class", "col-sm").append("KOMENTARZE:").append("<br/>").appendTo(detailsDiv);
                    var commentsSpan = $("<span>").append(data.numberOfComments).appendTo(commentsDiv);
                    var dataDiv = $("<div>").attr("class", "col-sm").append("DATA:").append("<br/>").appendTo(detailsDiv);
                    var dataSpan = $("<span>").append(data.date).appendTo(dataDiv);

                    var textdiv = $("<div>").attr("class", "row row-margin").appendTo(articleDiv);
                    var shitDiv = $("<div>").attr("class", "col-sm").appendTo(textdiv);
                    var properTextDiv = $("<div>").attr("class", "article-text").appendTo(shitDiv);
                    var text = $("<div>").append(data.articleDescription).appendTo(properTextDiv);

                    var link = "/article/" + data.id;
                    var a = $("<a>").attr("href",link).append("CZYTAJ DALEJ").appendTo(properTextDiv);

                    articleDiv.appendTo("#articles");

                    var scrollTo = $("<div>").attr("id", "scroll-to");
                    var scrollimg = $("<img>").attr("src", "/images/load.gif").appendTo(scrollTo);
                    lastId--;
                    if(lastId > 0){
                        loaded = false;
                    }
                    scrollTo.appendTo("#articles");
                    element_position = $('#scroll-to').offset().top;
                })}
            }
        );
});


