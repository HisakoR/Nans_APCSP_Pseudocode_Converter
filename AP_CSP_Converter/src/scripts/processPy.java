package scripts;

import java.util.ArrayList;
import java.util.List;

public class processPy {
    private ArrayList<String> data = new ArrayList<>();
    public void setData(ArrayList<String> datal){
        this.data = datal;
    }

    public void turnRefPy(){
        System.out.println("=====开始转换代码=====");
        for (int x = 0; x < data.size(); x++){
            String line = data.get(x);
            ArrayList<Integer> stringTarget = findString(line);
            /*
            原理：stringTarget中的双数位置为“的开头，而单数位置表示了”的结尾
            由此，操作数据时应当跳过双数位置的数值和单数位置数值中的任何数
            例{0,12,35,55}
            于是跳过string中0-12的位置与35-55的位置
            这样可以跳过用户代码中的字符串，由此避免错误的修改
            */
            //初始化位置
            ArrayList<String> listLine = new ArrayList<>();
            boolean isOdd = false;
            for (int z = 0; z < stringTarget.size(); z += 2){
                if(stringTarget.get(0) == 0){
                    System.out.println("检测到开头为string");
                    System.out.println("可用代码列表将从奇数开始");
                    isOdd = true;
                    z += 1;
                    listLine.add(line.substring(stringTarget.get(z), stringTarget.get(z + 1)));
                }
                else {
                    System.out.println("检测到开头为代码");
                    System.out.println("可用代码列表将从偶数开始");
                    listLine.add(line.substring(z, stringTarget.get(z)));
                }
            }
            /*
            向列表添加string部分和代码部分
            单数index为string部分，跳过
            偶数为代码部分，处理
             */
            for (int deta = 0; deta < listLine.size(); deta += 2){
                //处理行方法 while例
                if (listLine.get(deta).contains("while")){
                    listLine.set(deta, listLine.get(deta).replace("while", "REPEAT UNTIL"));
                }
                //处理行方法
            }
            String outputLine = "";
            for (String s : listLine) {
                outputLine = outputLine + s;
            }
            data.set(x, outputLine);
        }
    }
    public ArrayList<Integer> findString(String line){
        ArrayList<Integer> indexs = new ArrayList<>();
        for (int y = 0; y < line.length(); y++){
            if (line.substring(y , y + 1).contains("'")){
                indexs.add(y);
            }
        }
        return indexs;
    }

    public ArrayList<String> getData(){
        return data;
    }
}
