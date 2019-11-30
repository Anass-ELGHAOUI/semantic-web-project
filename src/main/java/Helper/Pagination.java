package Helper;

import lombok.Data;

@Data
public class Pagination {
    private int totalItems;
    private int totalItemPerPage;
    private int pageRange;
    private int totalPages;
    private int currentPage;

    public Pagination() {
        super();
    }

    public Pagination(int totalItems, int totalItemPerPage, int pageRange, int currentPage) {
        this.totalItems = totalItems;
        this.totalItemPerPage = totalItemPerPage;
        this.pageRange = (pageRange % 2 == 0) ? ++pageRange : pageRange;
        this.totalPages =  (this.totalItems % this.totalItemPerPage != 0) ? (this.totalItems / this.totalItemPerPage) + 1 : (this.totalItems / this.totalItemPerPage);
        this.currentPage = currentPage;
    }


    public String  showPagination(String link){
        String pagination = "";
        String prev = "";
        String next = "";
        String listePages = "";

        if(this.totalPages >= 1){
            prev = "<li class=\"page-item disabled\">\n" +
                    "   <a class=\"page-link\"  aria-label=\"Previous\">\n" +
                    "       <span aria-hidden=\"true\">&laquo;</span>\n" +
                    "       <span class=\"sr-only\">Previous</span>\n" +
                    "   </a>\n" +
                    "</li>";

            if (this.currentPage > 1){
                prev = "<li class=\"page-item \">\n" +
                        "   <a class=\"page-link\" href=\"" + link + "?page=" + (this.currentPage - 1) + "\" aria-label=\"Previous\">\n" +
                        "       <span aria-hidden=\"true\">&laquo;</span>\n" +
                        "       <span class=\"sr-only\">Previous</span>\n" +
                        "   </a>\n" +
                        "</li>";
            }
            next = "<li class=\"page-item\">\n" +
                    "   <a class=\"page-link\" aria-label=\"Next\">\n" +
                    "       <span aria-hidden=\"true\">&raquo;</span>\n" +
                    "       <span class=\"sr-only\">Next</span>\n" +
                    "   </a>\n" +
                    "</li>\n";
            if (this.currentPage < this.totalPages){
                next = "<li class=\"page-item\">\n" +
                        "  <a class=\"page-link\" href=\"" + link + "?page=" + (this.currentPage + 1) + "\" aria-label=\"Next\">\n" +
                        "     <span aria-hidden=\"true\">&raquo;</span>\n" +
                        "     <span class=\"sr-only\">Next</span>\n" +
                        "  </a>\n" +
                        "</li>\n";
            }
            int startPage;
            int endPage;
            if (this.pageRange < this.totalPages) {
                startPage = this.currentPage - (this.pageRange - 1) / 2;
                endPage = this.currentPage + (this.pageRange - 1) / 2;
                if (startPage < 1) {
                    startPage = 1;
                    endPage = this.pageRange;
                } else if (endPage > this.totalPages) {
                    startPage = this.totalPages - this.pageRange + 1;
                    endPage = this.totalPages;
                }
            } else {
                startPage = 1;
                endPage = this.totalPages ;
            }
            for (int i = startPage; i <= endPage; i++) {
                if (this.currentPage == i) {
                    listePages += "<li class=\"page-item active\">" +
                                    " <a class=\"page-link\">" + i + "</a>" +
                                  "</li>";
                } else {
                    listePages += "<li class=\"page-item \">" +
                            " <a class=\"page-link\" href=\"" + link + "?page=" + i + "\">" + i + "</a>" +
                            "</li>";
                }
            }
            pagination = "<nav class=\"d-flex justify-content-center wow fadeIn\">" +
                    "<ul class=\"pagination pg-red\">" + prev + listePages + next +
                    "</ul>" +
                    "</nav>";
        }
        return pagination;
    }
}
