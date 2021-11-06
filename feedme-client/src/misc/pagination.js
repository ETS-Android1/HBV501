function initPage(mainList, pageInfo){
    pageInfo.listCount = mainList.length;
    if (pageInfo.listCount < pageInfo.pageSize) {
        pageInfo.historyList = mainList;
    } else {
        pageInfo.historyList = mainList.slice(0, pageInfo.pageSize);
    }
    return pageInfo;
}

function updatePage(pageIndex, mainList, pageInfo) {
    let _start = (pageIndex - 1) * pageInfo.pageSize;
    let _end = pageIndex * pageInfo.pageSize;
    pageInfo.historyList = mainList.slice(_start, _end);
    pageInfo.page = pageIndex;
    return pageInfo;
}

function pages(pageInfo) {
    if (pageInfo.pageSize == null || pageInfo.listCount == null) return 0;
    return Math.ceil(pageInfo.listCount / pageInfo.pageSize);
}

export { updatePage, initPage, pages }