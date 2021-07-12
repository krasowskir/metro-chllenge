package uebung;

public class MultiDimensionalArray {

    public void testMultiDimensionalArrays(){
        String[][] multiArray = arrayInfos();
        String[][] paramInfos = this.getParameterInfos();

        for (int i = 0; i < multiArray.length; i++){
            for (int j = 0; j < multiArray[i].length; j++){
                System.out.println(multiArray[i][j]);
            }
        }

        System.out.println("==== param infos ====");
        for (int i = 0; i < paramInfos.length; i++){
            for (int j = 0; j < paramInfos[i].length; j++){
                System.out.println(paramInfos[i][j]);
            }
        }

    }
    public String[][] arrayInfos(){
        String[][] info = new String[10][10];

        for (int i = 0; i < info.length; i++){
            for (int j = 0; j < info[i].length; j++){
                info[i][j] = "String[" + i + "," + j + "]";
            }
        }
        return info;
    }

    public String[][] getParameterInfos(){
        String[][] paramInfos = new String[][]{
                {"fontsize",    "9-18",    "Size of font"},
                {"URL", "-", "Where to download"}
        };
        return paramInfos;
    }
}
