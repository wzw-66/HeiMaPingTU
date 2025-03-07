package game;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //菜单初始化
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");
    //计步器
    int count = 0;
    //标记一下0的位置
    int x = 0;
    int y = 0;
    int[][] arr2 = new int[4][4];
    //胜利二维数组
    int[][] win = new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化数据（打乱）
        initData();
        //初始化菜单
        initMenu();

        //初始化图片
        initImage();

        //设置界面可视
        this.setVisible(true);

    }

    private void initData() {

        int[] arr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int temp = 0;
        Random r = new Random();
        for (int i = 0; i < arr1.length; i++) {
            int index = r.nextInt(16);
            temp = arr1[i];
            arr1[i] = arr1[index];
            arr1[index] = temp;
        }
//        for (int i = 0; i < arr1.length; i++) {
//            System.out.print(arr1[i] + " ");
//        }
        System.out.println();
        for (int i = 0; i < 16; i++) {
            if (arr1[i] == 0) {
                y = i % 4;
                x = i / 4;
            }
            arr2[i / 4][i % 4] = arr1[i];
        }
//        for(int i = 0;i<arr2.length;i++){
//            for(int j = 0;j < arr2[i].length;j++){
//                System.out.print(arr2[i][j]+" ");
//            }
//            System.out.println();
//        }

    }

    //初始化图片
    private void initImage() {
        //删除之前的图片
        this.getContentPane().removeAll();
        //查看是否胜利
        if(victory()){
            JLabel win = new JLabel(new ImageIcon("image\\win.png"));
            win.setBounds(203,283,197,73);
            this.getContentPane().add(win);
        }
        //计步器
        JLabel jCount = new JLabel("步数："+count);
        jCount.setBounds(50,30,100,20);
        this.getContentPane().add(jCount);
        //初始化图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon image = new ImageIcon("image\\animal\\animal4\\" + arr2[i][j] + ".jpg");
                JLabel jLabel = new JLabel(image);
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(0));
                this.getContentPane().add(jLabel);
            }
        }
        //初始化背景
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);


        //刷新图片
        this.getContentPane().repaint();
    }

    private void initMenu() {
        JMenuBar jMenuBar = new JMenuBar();


        //创建菜单标题
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");


        //创建菜单下选项

        //给菜单选项添加事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //将选项加入进菜单
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //将菜单加入菜单条
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面大小
        this.setSize(603, 680);
        //设置标题
        this.setTitle("拼图单机 v1.0");
        //设置页面置顶
        this.setAlwaysOnTop(true);
        //设置页面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消居中设置(把隐藏容器中的居中设置取消)
        setLayout(null);
        //给整个界面添加监听
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //查看全局图片
        if(e.getKeyCode() == 65){
            this.getContentPane().removeAll();

            JLabel all = new JLabel(new ImageIcon("image\\animal\\animal4\\all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()){
            return;
        }
        int code = e.getKeyCode();
        System.out.println(code);
        //向左移动
        if (code == 38) {
            if (x == 3){
                return;
            }else{
                System.out.println("向左移动");
                arr2[x][y] = arr2[x+1][y];
                arr2[x+1][y] = 0;
                x++;
                count++;
                initImage();
            }
            //向上移动
        } else if (code == 37) {
            if (y == 3){
                return;
            }else{
                System.out.println("向上移动");
                arr2[x][y] = arr2[x][y+1];
                arr2[x][y+1] = 0;
                y++;
                count++;
                initImage();
            }
            //向右移动
        } else if (code == 40) {
            if (x == 0){
                return;
            }else{
                System.out.println("向右移动");
                arr2[x][y] = arr2[x-1][y];
                arr2[x-1][y] = 0;
                x--;
                count++;
                initImage();
            }
            //向下移动
        } else if (code == 39) {
            if (y == 0){
                return;
            }else{
                System.out.println("向左移动");
                arr2[x][y] = arr2[x][y-1];
                arr2[x][y-1] = 0;
                y--;
                count++;
                initImage();
            }
        }else if(code == 65){
            initImage();
            //作弊码
        }else if(code == 87){
            arr2 = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    public boolean victory(){
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if(win[i][j] != arr2[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if(object == replayItem){
            System.out.println("重新开始");
            count = 0;
            initData();
            initImage();
        }else if(object == reLoginItem){
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();
        }else if(object == closeItem){
            System.out.println("关闭游戏");
            System.exit(0);
        }else if(object == accountItem){
            System.out.println("关于我们");
            //创建对话框
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image\\about.png"));
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);


            jDialog.setSize(344,344);
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setVisible(true);
        }
    }
}
