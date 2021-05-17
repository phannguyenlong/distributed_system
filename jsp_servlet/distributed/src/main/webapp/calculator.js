$(document).ready(function () {
    if (Cookies.get("isLogin") == null)
		window.location.replace("/distributed/index.html");
})

function sumbitData() {
  $.ajax({
    type: "GET",
    async: false, // turn of async for reload table properly
    url: "http://localhost:8080/distributed/calculate?input=" + encodeURIComponent($("#answer").val()),
    success: (data) => {
        console.log(data);
        $("#answer").val(data)
    },
    error: (xhr, ajaxOptions, thrownError) =>
        console.log("wrong")
  });
}
