import java.util.HashMap;

//Used to store seen labels, to be referred to on second pass for offset or addr location (depending on how we want to implement it)
public class LabelTable implements ILabelTable {
    private final HashMap<String, Integer> labelTable;

    public LabelTable(){
        labelTable = new HashMap<String, Integer>();
    }

    //Set Label
    public void setLabel(String label, int addr){
        labelTable.put(label, addr);
    }

    //Get Label Address Code
    public int getAddr(String label) {
        return labelTable.getOrDefault(label, -1);
    }
}
