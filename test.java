class test{

  public static void main(String[] args){
    
    int n=4;
    int[][] a=new int[4][4];
    generate.doSchedule(n,a);
    for(int i=0;i<a.length;++i){
      for(int j=0;j<a[0].length;++j){
      System.out.print(a[i][j]+" ");
      }
      System.out.println();
    }
  }
}