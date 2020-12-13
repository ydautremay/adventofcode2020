package dyve.aoc2020.day.day4;

import java.util.Map;

public record Passport(String byr, String iyr, String eyr, String hgt, String hcl, String ecl, String pid, String cid) {

    public static Passport fromMap(Map<String, String> data){
        return new Passport(data.get("byr"), data.get("iyr"), data.get("eyr"), data.get("hgt"), data.get("hcl"), data.get("ecl"), data.get("pid"), data.get("cid"));
    }

    public boolean isValid(){
        return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null;
    }

    public boolean isValid2(){
        System.out.println(this);
        try {
            if(byr.matches("(\\d{4})")) {
                if(!(Integer.parseInt(byr) >= 1920 && Integer.parseInt(byr) <= 2002)){
                    System.out.println("invalid byr");
                    return false;
                }
            }else{
                System.out.println("invalid byr");
                return false;
            }

            if(iyr.matches("(\\d{4})")) {
                if(!(Integer.parseInt(iyr) >= 2010 && Integer.parseInt(iyr) <= 2020)){
                    System.out.println("invalid iyr");
                    return false;
                }
            }else{
                System.out.println("invalid iyr");
                return false;
            }

            if(eyr.matches("(\\d{4})")) {
                if(!(Integer.parseInt(eyr) >= 2020 && Integer.parseInt(eyr) <= 2030)){
                    System.out.println("invalid eyr");
                    return false;
                }
            }else{
                System.out.println("invalid eyr");
                return false;
            }

            int height = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            if(hgt.matches("\\d+cm")){
                if(!(height >= 150 && height <= 193)){
                    System.out.println("invalid hgt");
                    return false;
                }
            }else if(hgt.matches("\\d+in")){
                if(!(height >= 59 && height <= 76)){
                    System.out.println("invalid hgt");
                    return false;
                }
            }else{
                System.out.println("invalid hgt");
                return false;
            }

            if(!hcl.matches("#\\w{6}")){
                System.out.println("invalid hcl");
                return false;
            }

            if(!(ecl.equals("amb") || ecl.equals("blu") || ecl.equals("brn") || ecl.equals("gry") || ecl.equals("grn") || ecl.equals("hzl") || ecl.equals("oth"))){
                System.out.println("invalid ecl");
                return false;
            }

            if(!pid.matches("\\d{9}")){
                System.out.println("invalid pid");
                return false;
            }

            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String toString(){
        return String.format("byr : %s, iyr : %s, eyr : %s, hgt : %s, hcl : %s, ecl : %s, pid : %s, cid : %s", byr, iyr, eyr, hgt, hcl, ecl, pid, cid);
    }
}
