import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//�����࣬������Ϸ������
public class UI{
	
	private JFrame frame;//��������Ϸ����
	
	//�������̡��ؼ���
	private Chessboard chessboard = new Chessboard();//��������
	//*****������ҵ���߼����ؼ���	
	private Chess chess = new Chess();	

	private JMenuBar menu;//�˵���
	private JMenu option;//�˵����еġ�ѡ��˵�
	private Action replayOption;//��ѡ��������еġ�����һ�̡�ѡ��
	private Action AIFirstOption;//��ѡ��������еġ��������֡�ѡ��
	private Action HumanFirstOption;//��ѡ��������еġ��������֡�ѡ��

	//��Ϸ�������
	public static void main(String[] args){
		new UI().init();
	}	

	//�����������Ϸ����
	public void init(){

		frame = new JFrame("�˻���ս������");//������Ϸ���洰��
		menu = new JMenuBar();//�����˵���
		option = new JMenu("ѡ��");//�����˵����еġ�ѡ��˵�
		
		//�ѡ�ѡ��˵����뵽�˵���
		menu.add(option);
		
		//�ѡ�����һ�̡������������֡������������֡����롰ѡ���������
		replayOptionInit();
		option.add(replayOption);
		AIFirstOptionInit();
		option.add(AIFirstOption);
		HumanFirstOptionInit();
		option.add(HumanFirstOption);		

		frame.setJMenuBar(menu);//��menu����Ϊframe�Ĳ˵���
		frame.add(chessboard);//���������̼��뵽frame

		//��ʼ������
		chessboard.init();
		chess.init();	
		
		//����������ġ�����������¼���Ҫ�����ˣ�Ϊ�˱���д���õĳ��󷽷���ʵ�֣���������
		chessboard.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				//��������������¼������������¼��ȽϷ�����Ϊ�˿�һ������
				play(e);
			}
		});	

		//����frame�������Ͻ�ͼ��
		frame.setIconImage(frame.getToolkit().getImage("image/gobang.png"));
		frame.setSize(518, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(true);
	}
	
	

	//������һ�̡�ѡ�����Ӧ�Ĵ����¼�
	public void replayOptionInit(){
		replayOption = new AbstractAction("����һ��", new ImageIcon("image/replay.png")){
			public void actionPerformed(ActionEvent e){
				chessboard.init();//���淽�棺��ʼ������
				chess.init();//�߼�ҵ���棺��ʼ������
			}
		};
	}

	//���������֡�ѡ�����Ӧ�Ĵ����¼�
	public void AIFirstOptionInit(){
		AIFirstOption = new AbstractAction("��������", new ImageIcon("image/robot.png")){
			public void actionPerformed(ActionEvent e){
				//���̻�û�����ӵ�ʱ�����ѡ�񡰻������֡���һ�������ӣ�ѡ�񡰻������֡�ʧЧ
				if(chessboard.isEmpty()){
					Chess.FIRST = -1;
					//�������֣��������м�λ����һ������
					chessboard.addChessman(7, 7, -1);
					chess.addChessman(7, 7, -1);
				}
			}
		};
	}

	//���������֡�ѡ�����Ӧ�Ĵ����¼�
	public void HumanFirstOptionInit(){
		HumanFirstOption = new AbstractAction("��������", new ImageIcon("image/human.png")){
			public void actionPerformed(ActionEvent e){
				//���̻�û�����ӵ�ʱ�����ѡ���������֡���һ�������ӣ�ѡ���������֡�ʧЧ
				if(chessboard.isEmpty()){	
					Chess.FIRST = 1;
				}
			}
		};
	}
	
	//����������ҵ���߼�������������������¼�
	public void play(MouseEvent e){
		int cellSize = chessboard.getCellSize();//ÿ�����ӵı߳�
		int x = (e.getX() - 5) / cellSize;//����ֵת������������
		int y = (e.getY() - 5) / cellSize;//����ֵת������������
		//�ж������Ƿ�Ϸ�
		boolean isLegal = chess.isLegal(x, y);
		//������ӺϷ�
		if(isLegal){
			chessboard.addChessman(x, y, 1);//���淽���һ������
			chess.addChessman(x, y, 1);//�߼�ҵ�����һ������
			
			//�ж������Ƿ�ʤ��
			if(chess.isWin(x, y, 1)){
				JOptionPane.showMessageDialog(frame, "�����ʤ", "Congratulations����Ӯ�ˣ�", JOptionPane.PLAIN_MESSAGE);
				chessboard.init();
				chess.init();
				return;
			}
			
			//��������
			Location loc = chess.searchLocation();
			chessboard.addChessman(loc);
			chess.addChessman(loc.getX(), loc.getY(), loc.getOwner());

			//�жϻ����Ƿ�ʤ��
			if(chess.isWin(loc.getX(), loc.getY(), -1)){
				JOptionPane.showMessageDialog(frame, "������ʤ", "Congratulations�������ˣ�", JOptionPane.PLAIN_MESSAGE);
				chessboard.init();
				chess.init();
				return;
			}
		}
	}
	
}