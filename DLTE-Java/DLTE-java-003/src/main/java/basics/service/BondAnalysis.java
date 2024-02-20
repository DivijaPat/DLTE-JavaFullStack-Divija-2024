package basics.service;
public class BondAnalysis {
    public static void main(String[] args) {
        Bond myBond[]={
                new Bond(1200,7.5,"payable","Anupama",3),
                new Bond(1340,2.5,"payable","Meghana",2),
                new Bond(1245,3.4,"not payable","Keerthana",5),
                new Bond(2200,6.1,"payable","Sandeep",3),
                new Bond(3203,3.7,"not payable","Vani",4),

        };
        BondAnalysis analysis=new BondAnalysis();
        analysis.sort(myBond);
    }

    public void sort(Bond[] myBond) {
        System.out.println("Before sorting:");
        for (Bond each : myBond) {
            System.out.println(each.getRateOfInterest());
        }
        for (int index = 0; index < myBond.length; index++) {
            for (int next = 0; next < myBond.length - index - 1; next++) {
                if(myBond[next].getRateOfInterest().compareTo(myBond[next+1].getRateOfInterest())<0){
                    Bond backup= myBond[next];
                    myBond[next]=myBond[next+1];
                    myBond[next+1]=backup;
                }
            }
        }
        System.out.println("after sorting:");
        for (Bond each : myBond) {
            System.out.println(each.getRateOfInterest());
        }
    }
}
