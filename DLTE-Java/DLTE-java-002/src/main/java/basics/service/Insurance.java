package basics.service;
import java.util.Scanner;
public class Insurance {

    public static void main(String[] args) {
        String StarHealth[]={"hospitalization coverage","checkups","cashless treatments"};
        String AdithyaBirlaHealthInsure[]={"daycare procedures","hospitalization coverages","accidental hospitalization" };
        String RelianceGeneral[]={"critical illness cover","complimentary checkups","maternity benefits"};
        Scanner scanner=new Scanner(System.in);
        String feature1="";
        System.out.println("enter feature:");
        feature1= scanner.nextLine();
        String companyOffer="";
        for(int index=0;index<StarHealth.length;index++){
            if(feature1.toLowerCase().contains(StarHealth[index]))
                companyOffer+="StarHealthInsurance";

        }
        for(int index=0;index<AdithyaBirlaHealthInsure.length;index++){
            if(feature1.toLowerCase().contains(AdithyaBirlaHealthInsure[index]))
                companyOffer+="AdithyaBirlaHealthInsure";

        }
        for(int index=0;index<RelianceGeneral.length;index++){
            if(feature1.toLowerCase().contains(RelianceGeneral[index]))
                companyOffer+="RelianceGeneral";

        }
        System.out.println("companies that offer features are "+companyOffer);
    }
}
