function getDetail() {
    var details = document.getElementsByClassName("myDetail");
    for (var i = 0; i < details.length; i++) {
        $.ajax("/web/admin/add-product/add-details", {
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                name: details[i].name,
                value: details[i].value
            })
        })
    }
    window.location.href = "third";
}

function deleteFromCart(id) {
    var productId = document.getElementById("productId" + id).getAttribute("value");
    var amount = document.getElementById("amount" + id).value;
    console.log(productId);
    console.log(amount);

    $.ajax("/user/cart/delete", {
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            productId: productId,
            amount: amount
        })
    }).done (function redirect(amount) {
        $("#amountProductsInCart").text(amount.amount);
        window.location.href = "/user/cart/";
    })
}

function addToCart(id) {
    var productId = document.getElementById("productId" + id).getAttribute("value");
    var amount = document.getElementById("amount" + id).value;
    console.log(productId);
    console.log(amount);

    $.ajax("/user/cart/add", {
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            productId: productId,
            amount: amount
        })
    }).done (function redirect(amount) {
        $("#amountProductsInCart").text(amount.amount);
    })
}

function getFilters() {
    var currentUrl = window.location.href.toString().split("?")[0];
    var newUrl = currentUrl;
    var filters = document.getElementsByClassName("filters");

    for (i = 0; i < filters.length; i++) { //processing year
        if(filters[i].dataset.filter == 'year' && filters[i].type == 'checkbox' && filters[i].checked) {
            newUrl = addDelimiter(newUrl);
            newUrl += filters[i].dataset.filter + '=' + filters[i].dataset.filterValue;
        } else if(filters[i].type == 'select-one' && filters[i].value != '') {
            newUrl = addDelimiter(newUrl);
            newUrl += filters[i].dataset.filter + filters[i].dataset.fromTo + '=' + filters[i].value;
        }
    }

    for(i = 0; i < filters.length; i++) { //processing price
        if(filters[i].dataset.filter == 'price' && filters[i].value != '') {
            newUrl = addDelimiter(newUrl);
            newUrl += filters[i].dataset.filter + filters[i].dataset.fromTo + '=' + filters[i].value;
        }
    }

    for(i = 0; i < filters.length; i++) { //processing OS
        if(filters[i].dataset.filter == 'os' && filters[i].checked) {
            newUrl = addDelimiter(newUrl);
            newUrl += filters[i].dataset.filter + '=' + filters[i].value;
        }
    }

    for(i = 0; i < filters.length; i++) { //processing producer
        if(filters[i].dataset.filter == 'producer' && filters[i].checked) {
            newUrl = addDelimiter(newUrl);
            newUrl += filters[i].dataset.filter + '=' + filters[i].value;
        }
    }

    console.log(newUrl);
    window.location.href = newUrl;
}

function addDelimiter(url) {
    if (url.indexOf('?') >= 0) {
        url += '&';
    } else {
        url += '?';
    }
    return url;
}

