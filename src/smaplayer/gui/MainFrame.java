/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smaplayer.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import fileClasses.FileUtils;
import fileClasses.Mp3;
import fileClasses.PlayerFileFilter;
import static jdk.nashorn.internal.objects.NativeArray.map;
import smaplayer.SmaPlayer;
import java.util.ArrayList;

/**
 *
 * @author Nick
 */
public class MainFrame extends javax.swing.JFrame implements BasicPlayerListener {
    
    //Два объекта для открытия файлов MP3 и PLS
    private PlayerFileFilter openMP3 = new PlayerFileFilter(FileUtils.MP3_FILES_EXP, FileUtils.MP3_FILES_DESC);
    private PlayerFileFilter openPLS = new PlayerFileFilter(FileUtils.PLS_FILES_EXP, FileUtils.PLS_FILES_DESC);
    
    //переменные
    private Playlist playlist; //Плейлист для доступа к окну Playlist
    private int playedSong; //Индекс играющей песни для переключения мелодий
    private SmaPlayer doing; //Объект плеера. 
    
    //Переменые для ползунка статуса песни
    private long songTime; //Длительность песни
    private long timeFromStart; //Прошло секунд с начала песни
    private int songSize; //Размер песни в байтах
    private double songPosition = 0.0; //Позиция ползунка
    //Передвижение ползунка (вручную\автоматически)
    private boolean autoMove = true;
    //Проверка отпускания ползунка
    private boolean check = false;
    //Запись значений перемотки
    private ArrayList<Double> revPosition = new ArrayList();
    

    /**
     * Creates new form MainFrame
     * @param playlist
     */
    public MainFrame(Playlist playlist, SmaPlayer player) {
        initComponents();
        this.playlist = playlist;        
        this.doing = player;
        doing.setBasicPlayerListener(this);
        
        //передаем элементы интерфейса в плеер
        doing.setUIElements(lbSongName, btnPlay);        
        //Устанавливаем громкость
        doing.setVolume(slVolume.getValue(), slVolume.getMaximum());
    }
    
    @Override
    public void opened(Object o, Map map) {
        //Определяем длину песни и ее размер в байтах        
        songTime = (long) Math.round((((Long) map.get("duration")).longValue()) / 1000000);
        songSize = (int) Math.round(((Integer) map.get("mp3.length.bytes")).intValue());  
    }
    @Override
    public void progress(int bytesread, long l, byte[] bytes, Map map) {
       float progress = -1.0f;
       
       if (bytesread > 0 && songTime > 0){
           progress = (bytesread * 1.0f) / (songSize * 1.0f);
       }
       
       //Сколько секунд прошло       
       timeFromStart = (long) (progress * songTime);
       
       lbSongTime.setText(doing.getTime(timeFromStart));
       
       if (songTime != 0){
           if (autoMove){
               slSong.setValue(((int) Math.round((timeFromStart * 100) / songTime)));
           }
       }
    }
    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
        int state = bpe.getCode();
        
        if (state == BasicPlayerEvent.PLAYING)
            autoMove = true;
        else if (state == BasicPlayerEvent.SEEKING)
            autoMove = false;
        else if (state == BasicPlayerEvent.EOM)
            nextSong();
    }
    @Override
    public void setController(BasicController bc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void processSeek(double bytes){
        try {
            long skipBytes = (long) Math.round(((Integer) songSize).intValue() * bytes);
            System.out.println("пропустить байт: "+ skipBytes);
            doing.jump(skipBytes);
        } catch (Exception e) {
            e.printStackTrace();
            autoMove = true;
        }
    }
    
    private void nextSong(){
        int sizeOfList = playlist.getListModel().getSize()-1;        
        playedSong = playlist.getList().getSelectedIndex();
        if (playedSong < sizeOfList){
            playedSong++;            
            playlist.getList().setSelectedIndex(playedSong);
            playSong();
        }
    }    
    private void prevSong(){  
        playedSong = playlist.getList().getSelectedIndex();
        if (playedSong > 0){            
            playedSong--;
            playlist.getList().setSelectedIndex(playedSong);            
            playSong();
        }
    }
    private void playSong(){
        int selectedSong = playlist.getList().getSelectedIndex();
        if (selectedSong != -1){
            //Выводим название песни.
            Mp3 mp3 = (Mp3) playlist.getListModel().getElementAt(selectedSong);            
            
            //Начинаем проигрывание песни.
            doing.play(mp3.getSongPatch());
            doing.setVolume(slVolume.getValue(), slVolume.getMaximum());
        }    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jOpenFileDialog = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        pnlStatus = new javax.swing.JPanel();
        slSong = new javax.swing.JSlider();
        lbSongName = new javax.swing.JLabel();
        lbSongTime = new javax.swing.JLabel();
        slPan = new javax.swing.JSlider();
        btnPlayList = new javax.swing.JButton();
        pnlButtons = new javax.swing.JPanel();
        btnPrevSong = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnNextSong = new javax.swing.JButton();
        btnVollume = new javax.swing.JToggleButton();
        slVolume = new javax.swing.JSlider();
        btnStop = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuOpenFile = new javax.swing.JMenuItem();
        jMenuOpenPlaylist = new javax.swing.JMenuItem();
        jMenuSavePlayList = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();

        jOpenFileDialog.setAcceptAllFileFilterUsed(false);
        jOpenFileDialog.setMultiSelectionEnabled(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmaPlayer v. 0.2");
        setIconImage(new ImageIcon("src/smaplayer/images/mainIcon.png").getImage());
        setLocation(new java.awt.Point(500, 500));
        setMinimumSize(new java.awt.Dimension(350, 172));
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        pnlStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        slSong.setMinorTickSpacing(15);
        slSong.setToolTipText("");
        slSong.setValue(0);
        slSong.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slSongStateChanged(evt);
            }
        });

        lbSongTime.setText("00:00:00");

        slPan.setMaximum(200);
        slPan.setMinorTickSpacing(10);
        slPan.setValue(100);
        slPan.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slPanStateChanged(evt);
            }
        });

        btnPlayList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/playlist.png"))); // NOI18N
        btnPlayList.setToolTipText("Плейлист");
        btnPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenPlaylist(evt);
            }
        });

        javax.swing.GroupLayout pnlStatusLayout = new javax.swing.GroupLayout(pnlStatus);
        pnlStatus.setLayout(pnlStatusLayout);
        pnlStatusLayout.setHorizontalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStatusLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(slSong, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlStatusLayout.createSequentialGroup()
                        .addGroup(pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbSongName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSongTime, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slPan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(174, 174, 174))
        );
        pnlStatusLayout.setVerticalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatusLayout.createSequentialGroup()
                .addGroup(pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStatusLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbSongName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbSongTime))
                    .addGroup(pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(slPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slSong, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlButtons.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnPrevSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/prevsong.png"))); // NOI18N
        btnPrevSong.setToolTipText("Предыдущая песня");
        btnPrevSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevSongActionPerformed(evt);
            }
        });

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/play.png"))); // NOI18N
        btnPlay.setToolTipText("Воспроизвести");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnNextSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/nextsong.png"))); // NOI18N
        btnNextSong.setToolTipText("Следующая песня");
        btnNextSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextSongActionPerformed(evt);
            }
        });

        btnVollume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/volume.png"))); // NOI18N
        btnVollume.setToolTipText("Громкость");
        btnVollume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVollumeActionPerformed(evt);
            }
        });

        slVolume.setMaximum(200);
        slVolume.setMinorTickSpacing(5);
        slVolume.setSnapToTicks(true);
        slVolume.setToolTipText("");
        slVolume.setValue(100);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, btnVollume, org.jdesktop.beansbinding.ELProperty.create("${!selected}"), slVolume, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        slVolume.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slVolumeStateChanged(evt);
            }
        });

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/stop.png"))); // NOI18N
        btnStop.setToolTipText("Стоп");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrevSong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNextSong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVollume, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNextSong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrevSong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(slVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlButtonsLayout.createSequentialGroup()
                        .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVollume, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStop))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 366, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        jMenuFile.setText("Файл");

        jMenuOpenFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuOpenFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/openfile.png"))); // NOI18N
        jMenuOpenFile.setText("Открыть файл");
        jMenuOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOpenFileActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuOpenFile);

        jMenuOpenPlaylist.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuOpenPlaylist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/openplaylist.png"))); // NOI18N
        jMenuOpenPlaylist.setText("Открыть плейлист");
        jMenuOpenPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOpenPlaylistActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuOpenPlaylist);

        jMenuSavePlayList.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuSavePlayList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/savefile.png"))); // NOI18N
        jMenuSavePlayList.setText("Сохранить плейлист");
        jMenuSavePlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSavePlayListActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuSavePlayList);
        jMenuFile.add(jSeparator1);

        jMenuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/exit.png"))); // NOI18N
        jMenuExit.setText("Закрыть программу");
        jMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuExit);

        jMenuBar.add(jMenuFile);

        jMenuEdit.setText("Сервис");
        jMenuBar.add(jMenuEdit);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Открываем окно плейлиста справа от основного плеера.
    private void OpenPlaylist(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenPlaylist
        if (playlist.isShowing()){
            playlist.setVisible(false);
        }
        else{
            Point coordinates = this.getLocation();
            double x = coordinates.getX();
            double y = coordinates.getY();

            playlist.setLocation((int)x+400, (int)y);
            playlist.setVisible(true);            
        }        
    }//GEN-LAST:event_OpenPlaylist
    //Открытие файлов через меню.
    private void jMenuOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuOpenFileActionPerformed
        FileUtils.setFileFilter(jOpenFileDialog, openMP3);        
        int count = jOpenFileDialog.showOpenDialog(this);
        int coin = 0;
        
        Mp3 playlistSongName;
        
        if (count == JFileChooser.APPROVE_OPTION){
            File[] files = jOpenFileDialog.getSelectedFiles();
            
            for (File file: files) {
                Mp3 mp3 = new Mp3(file.getName(), file.getPath());
                
                for (int i = 0; i < playlist.getListModel().size(); i++) {
                    playlistSongName = (Mp3) playlist.getListModel().getElementAt(i);
                    if (mp3.getSongName().equals(playlistSongName.getSongName()))
                        coin++;                    
                }
                
                if(coin == 0)
                    playlist.setListModel(mp3);
                else
                    coin = 0;
            }
        }
        playlist.getList().setSelectedIndex(0);
    }//GEN-LAST:event_jMenuOpenFileActionPerformed
    //Переключает на следующую песню.
    private void btnNextSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextSongActionPerformed
        nextSong();
    }//GEN-LAST:event_btnNextSongActionPerformed
    //Включаем мелодию. Или ставим на паузу, если играет.
    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        playSong();
    }//GEN-LAST:event_btnPlayActionPerformed
    //Переключает на предыдущую песню.
    private void btnPrevSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevSongActionPerformed
        prevSong();
    }//GEN-LAST:event_btnPrevSongActionPerformed
    //Выход из программы.
    private void jMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuExitActionPerformed
    //Сохранение и открытие плейлиста.
    private void jMenuSavePlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSavePlayListActionPerformed
        FileUtils.setFileFilter(jOpenFileDialog, openPLS);        
        int count = jOpenFileDialog.showSaveDialog(this);
        
        if (count == JFileChooser.APPROVE_OPTION){
            File selectedFile = jOpenFileDialog.getSelectedFile();
            
            if (selectedFile.exists()){
                
                int resultOvveride = JOptionPane.showConfirmDialog(this, "Файл существует", "Перезаписать", JOptionPane.YES_NO_CANCEL_OPTION);
                switch (resultOvveride){
                    case JOptionPane.NO_OPTION:
                        jMenuSavePlayListActionPerformed(evt);
                        return;
                    case JOptionPane.CANCEL_OPTION:
                        jOpenFileDialog.cancelSelection();
                        return;
                }
                jOpenFileDialog.approveSelection();
            }
            String fileExp = FileUtils.getFileExp(selectedFile.getName());
            
            String result = (fileExp != null && fileExp.equals(FileUtils.PLS_FILES_EXP)) ? selectedFile.getPath() : selectedFile.getPath() +"."+ FileUtils.PLS_FILES_EXP;
            FileUtils.serialize(playlist.getListModel(), result);
        }
    }//GEN-LAST:event_jMenuSavePlayListActionPerformed
    private void jMenuOpenPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuOpenPlaylistActionPerformed
        FileUtils.setFileFilter(jOpenFileDialog, openPLS);        
        DefaultListModel reserv;
        
        int count = jOpenFileDialog.showOpenDialog(this);        
        if (count == JFileChooser.APPROVE_OPTION){
            File selectFile = jOpenFileDialog.getSelectedFile();
            reserv = (DefaultListModel) FileUtils.deserialize(selectFile.getPath());
            playlist.setListModel(reserv);
        }
    }//GEN-LAST:event_jMenuOpenPlaylistActionPerformed
    //Выключение звука.
    private void btnVollumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVollumeActionPerformed
        if (btnVollume.isSelected())
            slVolume.setValue(0);
        else
            slVolume.setValue(10);
    }//GEN-LAST:event_btnVollumeActionPerformed
    //Выключает воспроизведение
    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        doing.stop();
    }//GEN-LAST:event_btnStopActionPerformed
    //Изменение громкости
    private void slVolumeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slVolumeStateChanged
        doing.setVolume(slVolume.getValue(), slVolume.getMaximum());        
    }//GEN-LAST:event_slVolumeStateChanged
    //Ползунок статуса дорожки.
    private void slSongStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slSongStateChanged
        //Проверяем трогает ли пользователь ползунок    
        if (slSong.getValueIsAdjusting() == false){            
            //Проверяем был ли передвинут ползунок
            if (check == true){
                check = false;                
                int index = revPosition.size() - 2; //Берем последнюю позицию ползунка.
                processSeek(revPosition.get(index)); //Перематываем на эту позицию.
                revPosition.clear();
            }
        }
        else{
            check = true;
            revPosition.add(slSong.getValue() * 1.0 / 100); //Записываем позиции ползунка
        }
    }//GEN-LAST:event_slSongStateChanged
    //Уровень "Лево|Право"
    private void slPanStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slPanStateChanged
        doing.changePan(slPan.getValue());
    }//GEN-LAST:event_slPanStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNextSong;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnPlayList;
    private javax.swing.JButton btnPrevSong;
    private javax.swing.JButton btnStop;
    private javax.swing.JToggleButton btnVollume;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenuItem jMenuExit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuOpenFile;
    private javax.swing.JMenuItem jMenuOpenPlaylist;
    private javax.swing.JMenuItem jMenuSavePlayList;
    private javax.swing.JFileChooser jOpenFileDialog;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lbSongName;
    private javax.swing.JLabel lbSongTime;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JSlider slPan;
    private javax.swing.JSlider slSong;
    private javax.swing.JSlider slVolume;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
