function validateSearch() {
    const input = document.querySelector('input[name="keyword"]');
    const keyword = input.value.trim();

    if (keyword === "") {
        alert("검색어를 입력하세요");
        input.focus();
        return false;
    }
    return true;
}
