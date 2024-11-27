public class Helpers {


    public String EncryptPassword(String Pwd){
        StringBuilder encryptedPwd = new StringBuilder();
        if (Pwd.length()<6){
            System.out.println("Entered Password is less than 6 Characters");
            return encryptedPwd.toString();
        }
        int lower = 0,upper = 0,number = 0;

        for (char c : Pwd.toCharArray()){
            if((int)c>=65 && (int)c<=90){
                upper++;
            } else if ((int)c>=97 && (int)c<=122) {
                lower++;
            } else if ((int)c>=48 && (int)c<=57) {
                number++;
            }
        }
        System.out.println(lower+":"+upper+":"+number+":"+Pwd);
        if (!(lower>=2 && upper>=2 && number>=2)){return encryptedPwd.toString();}

        for (char c : Pwd.toCharArray()) {
            if (c == '9') {
                encryptedPwd.append('0');
            } else if (c == 'Z') {
                encryptedPwd.append('A');
            } else if (c == 'z') {
                encryptedPwd.append('a');
            } else {
                encryptedPwd.append((char) (c + 1));
            }
        }

        return encryptedPwd.toString();
    }
}
