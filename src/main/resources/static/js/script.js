/**
 * Created by Dzejkob on 13.12.2017.
 */
function doFlickr(page) {
        var url = "https://api.flickr.com/services/rest/";
        var query =$("#search-query").val();
        if(query != ""){
            $.getJSON(url, {
                method: "flickr.photos.search",
                api_key: "8ecde9047b8e217602cea6fdcf2a7817",
                text: query,
                format: "json",
                per_page: 10,
                page: page,
                nojsoncallback: 1
            }).done(function (data) {
                $("#images").empty();
                if(data.stat === "fail"){
                    $("#images").append(data.message);
                }else {
                    var select = $("<select>")
                    select.attr("class", "image-picker");
                    select.attr("id", "select-image");
                    select.attr("name", "imageUrl");
                    select.appendTo("#images");
                    for (var i = 0; i < data.photos.photo.length; i++) {
                        var photo = data.photos.photo[i];
                        var option = $("<option>");
                        option.attr("data-img-src", "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + "_q.jpg");
                        option.attr("value", "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + "_b.jpg");
                        option.appendTo("#select-image");
                    }
                    $("select").imagepicker();
                    var more = $("<a>").attr("href", "#").attr("id", "more").append("--WIĘCEJ--").appendTo("#images");
                }
            })}
}
$(document).ready(function ()
{
    $("#flickr-selector-div").hide();
    $("#upload").click(function () {
        if ($("#upload").is(':checked')) {
            $("#flickr-selector-div").hide();
            $("#upload-selector-div").show();
        }
    });
    $("#flickr").click(function () {
        if ($("#flickr").is(':checked')) {
            $("#upload-selector-div").hide();
            $("#flickr-selector-div").show();
        }
    });
    var i = 1;
    $("#get-images-button").click(function () {
        i = 1;
        doFlickr(i);
    });
    $(document).on("click", "#more", function (event) {
        event.preventDefault();
        i++;
        doFlickr(i);
    });

});

