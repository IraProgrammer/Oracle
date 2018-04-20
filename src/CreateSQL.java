public class CreateSQL {

    public String createWhere(String text) {

        String sqlWhere = "";

        char[] txt = text.toCharArray();

        String lower = "";
        String upper = "";

        boolean flag = false;

        if (txt[0] != '<' && txt[0] != '>') {
            for (int i = 0; i < txt.length; i++) {
                if (txt[i] == '-') flag = true;
                else if (!flag) lower += txt[i];
                else if (flag) upper += txt[i];
            }
        }

        if (txt[0] == '<') {
            sqlWhere = "WHERE CODE_DOCUMENT < " + Integer.parseInt(text.substring(1, text.length()));
        }

        else if (txt[0] == '>') {
            System.out.println(text.substring(1, text.length()));
            sqlWhere = "WHERE CODE_DOCUMENT > " + Integer.parseInt(text.substring(1, text.length()));
        }

        else if (upper.equals("")) {
            sqlWhere = "WHERE CODE_DOCUMENT = " + Integer.parseInt(lower);
        }

        else if (!upper.equals("")){
            sqlWhere = "WHERE CODE_DOCUMENT BETWEEN " + Integer.parseInt(lower) + " AND " + Integer.parseInt(upper);
        }
        return sqlWhere;
    }

    public String createOrderByDate(){
        return "ORDER BY DATE_CREATE";
    }

    public String createOrderByNumber(){
        return "ORDER BY NUMBER_DOCUMENT";
    }
}
