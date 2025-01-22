const baseURL = "http://localhost:8080";

function deleteContact(stockUniqueId) {
    // Construct the URL for deleting the stock


    const url = `/user/delete/${stockUniqueId}`;
    window.location.replace(url);
}
