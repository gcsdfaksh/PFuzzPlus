//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import burp.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BurpExtender extends AbstractTableModel implements IBurpExtender, ITab, IHttpListener, IScannerCheck, IMessageEditorController, IContextMenuFactory {
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    private JSplitPane splitPane;
    private IMessageEditor requestViewer;
    private IMessageEditor responseViewer;
    private final List<LogEntry> log = new ArrayList();
    private final List<LogEntry> log2 = new ArrayList();
    private final List<LogEntry> log3 = new ArrayList();
    private final List<Request_md5> log4_md5 = new ArrayList();
    private IHttpRequestResponse currentlyDisplayedItem;
    JTextArea log_text;
    public PrintWriter stdout;
    int switchs = 1;
    int clicks_Repeater = 0;
    int clicks_Proxy = 0;
    int conut = 0;
    String data_md5_id;
    public AbstractTableModel model = new MyModel();
    int original_data_len;
    int is_int = 1;
    String temp_data;
    int JTextArea_int = 0;
    String JTextArea_data_1 = "";
    String diy_error_text = "";
    int diy_payload_1 = 1;
    int diy_payload_2 = 1;
    int select_row = 0;
    Table logTable;
    int is_cookie = -1;
    String white_URL = "";
    int white_switchs = 0;

    public BurpExtender() {
    }

    public void registerExtenderCallbacks(final IBurpExtenderCallbacks callbacks) {
        this.stdout = new PrintWriter(callbacks.getStdout(), true);
        this.stdout.println("hello PFuzzPlus!");
        this.stdout.println("你好 欢迎使用 PFuzzPlus!");
        this.stdout.println("version:1.0");
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();
        callbacks.setExtensionName("PFuzzPlus V1.0");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BurpExtender.this.splitPane = new JSplitPane(1);
                JSplitPane splitPanes = new JSplitPane(0);
                JSplitPane splitPanes_2 = new JSplitPane(0);
                JSplitPane jp = new JSplitPane(1);
                BurpExtender.this.logTable = BurpExtender.this.new Table(BurpExtender.this);
                JScrollPane scrollPane = new JScrollPane(BurpExtender.this.logTable);
                Table_log2 table = BurpExtender.this.new Table_log2(BurpExtender.this.model);
                JScrollPane pane = new JScrollPane(table);
                JPanel tab_jp1 = new JPanel();
                tab_jp1.setLayout(new GridLayout(1, 1));
                tab_jp1.add(scrollPane);
                JPanel tab_jp2 = new JPanel();
                tab_jp2.setLayout(new GridLayout(1, 1));
                tab_jp2.add(pane);
                jp.setLeftComponent(tab_jp1);
                jp.setRightComponent(tab_jp2);
                JPanel jps = new JPanel();
                jps.setLayout(new GridLayout(13, 1));
                JLabel jls = new JLabel("插件名：PFuzzPlus");
                JLabel jls_2 = new JLabel("版本：PFuzzPlus V1.0");
                final JCheckBox chkbox1 = new JCheckBox("启动插件", true);
                final JCheckBox chkbox2 = new JCheckBox("监控Repeater");
                final JCheckBox chkbox3 = new JCheckBox("监控Proxy");
                final JCheckBox chkbox4 = new JCheckBox("值是数字则进行-1、-0", true);
                final JCheckBox chkbox8 = new JCheckBox("测试Cookie");
                JLabel jls_5 = new JLabel("如果需要多个域名加白请用,隔开");
                final JTextField textField = new JTextField("填写白名单域名");
                JButton btn1 = new JButton("清空列表");
                final JButton btn3 = new JButton("启动白名单");
                JTabbedPane tab_diy = new JTabbedPane();
                JSplitPane yx1_sp = new JSplitPane(0);
                JPanel yx1_jp = new JPanel();
                yx1_jp.setLayout(new GridLayout(5, 1));
                JLabel jls_4 = new JLabel("修改payload后记得点击加载");
                final JCheckBox chkbox5 = new JCheckBox("自定义payload");
                final JCheckBox chkbox6 = new JCheckBox("自定义payload中空格url编码", true);
                final JCheckBox chkbox7 = new JCheckBox("自定义payload中参数值置空");
                JButton btn2 = new JButton("加载/重新加载payload");
                yx1_jp.add(jls_4);
                yx1_jp.add(chkbox5);
                yx1_jp.add(chkbox6);
                yx1_jp.add(chkbox7);
                yx1_jp.add(btn2);
                JPanel yx2_jp = new JPanel();
                yx2_jp.setLayout(new GridLayout(1, 1));
                final JTextArea jta = new JTextArea("%df' and sleep(3)%23\n'and '1'='1", 18, 16);

                try {
                    BufferedReader in = new BufferedReader(new FileReader("PFuzzPlus_diy_payload.ini"));

                    String str;
                    String str_data;
                    for(str_data = ""; (str = in.readLine()) != null; str_data = str_data + str + "\n") {
                    }

                    jta.setText(str_data);
                } catch (IOException var46) {
                }

                jta.setForeground(Color.BLACK);
                jta.setFont(new Font("楷体", 1, 16));
                jta.setBackground(Color.LIGHT_GRAY);
                jta.setEditable(false);
                JScrollPane jsp = new JScrollPane(jta);
                yx2_jp.add(jsp);
                yx1_sp.setLeftComponent(yx1_jp);
                yx1_sp.setRightComponent(yx2_jp);
                JSplitPane yx2_sp = new JSplitPane(0);
                JPanel yx3_jp = new JPanel();
                final JCheckBox chkbox_diy_error = new JCheckBox("自定义报错信息（支持正则表达式）", true);
                yx3_jp.add(chkbox_diy_error);
                JPanel yx4_jp = new JPanel();
                yx4_jp.setLayout(new GridLayout(1, 1));
                final JTextArea diy_error_jta = new JTextArea("ORA-\\d{5}\nSQL syntax.*?MySQL\nUnknown column\nSQL syntax\njava.sql.SQLSyntaxErrorException\nError SQL:\nSyntax error\n附近有语法错误\njava.sql.SQLException\n引号不完整\nSystem.Exception: SQL Execution Error!\ncom.mysql.jdbc\nMySQLSyntaxErrorException\nvalid MySQL result\nyour MySQL server version\nMySqlClient\nMySqlException\nvalid PostgreSQL result\nPG::SyntaxError:\norg.postgresql.jdbc\nPSQLException\nMicrosoft SQL Native Client error\nODBC SQL Server Driver\nSQLServer JDBC Driver\ncom.jnetdirect.jsql\nmacromedia.jdbc.sqlserver\ncom.microsoft.sqlserver.jdbc\nMicrosoft Access\nAccess Database Engine\nODBC Microsoft Access\nOracle error\nDB2 SQL error\nSQLite error\nSybase message\nSybSQLException", 18, 16);
                diy_error_jta.setEditable(false);
                diy_error_jta.setBackground(Color.LIGHT_GRAY);
                JScrollPane diy_error_jsp = new JScrollPane(diy_error_jta);
                BurpExtender.this.diy_error_text = diy_error_jta.getText();
                yx4_jp.add(diy_error_jsp);
                yx2_sp.setLeftComponent(yx3_jp);
                yx2_sp.setRightComponent(yx4_jp);
                JSplitPane yx3_sp = new JSplitPane(0);
                JPanel yx5_jp = new JPanel();
                JButton log_btn = new JButton("清空日志");
                yx5_jp.add(log_btn);
                JPanel yx6_jp = new JPanel();
                yx6_jp.setLayout(new GridLayout(1, 1));
                BurpExtender.this.log_text = new JTextArea("");
                BurpExtender.this.log_text.setEditable(false);
                JScrollPane log_sp = new JScrollPane(BurpExtender.this.log_text);
                yx6_jp.add(log_sp);
                yx3_sp.setLeftComponent(yx5_jp);
                yx3_sp.setRightComponent(yx6_jp);
                tab_diy.add("自定义sql语句", yx1_sp);
                tab_diy.add("自定义报错信息", yx2_sp);
                tab_diy.add("日志", yx3_sp);
                chkbox1.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (chkbox1.isSelected()) {
                            BurpExtender.this.stdout.println("插件PFuzzPlus启动");
                            BurpExtender.this.switchs = 1;
                        } else {
                            BurpExtender.this.stdout.println("插件PFuzzPlus关闭");
                            BurpExtender.this.switchs = 0;
                        }

                    }
                });
                chkbox2.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (chkbox2.isSelected()) {
                            BurpExtender.this.stdout.println("启动 监控Repeater");
                            BurpExtender.this.clicks_Repeater = 64;
                        } else {
                            BurpExtender.this.stdout.println("关闭 监控Repeater");
                            BurpExtender.this.clicks_Repeater = 0;
                        }

                    }
                });
                chkbox3.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (chkbox3.isSelected()) {
                            BurpExtender.this.stdout.println("启动 监控Proxy");
                            BurpExtender.this.clicks_Proxy = 4;
                        } else {
                            BurpExtender.this.stdout.println("关闭 监控Proxy");
                            BurpExtender.this.clicks_Proxy = 0;
                        }

                    }
                });
                chkbox4.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (chkbox4.isSelected()) {
                            BurpExtender.this.stdout.println("启动 值是数字则进行-1、-0");
                            BurpExtender.this.is_int = 1;
                        } else {
                            BurpExtender.this.stdout.println("关闭 值是数字则进行-1、-0");
                            BurpExtender.this.is_int = 0;
                        }

                    }
                });
                chkbox5.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (chkbox5.isSelected()) {
                            BurpExtender.this.stdout.println("启动 自定义payload");
                            jta.setEditable(true);
                            jta.setBackground(Color.WHITE);
                            BurpExtender.this.JTextArea_int = 1;
                            if (BurpExtender.this.diy_payload_1 == 1) {
                                String temp_data = jta.getText();
                                temp_data = temp_data.replaceAll(" ", "%20");
                                BurpExtender.this.JTextArea_data_1 = temp_data;
                            } else {
                                BurpExtender.this.JTextArea_data_1 = jta.getText();
                            }
                        } else {
                            BurpExtender.this.stdout.println("关闭 自定义payload");
                            jta.setEditable(false);
                            jta.setBackground(Color.LIGHT_GRAY);
                            BurpExtender.this.JTextArea_int = 0;
                        }

                    }
                });
                chkbox6.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (chkbox6.isSelected()) {
                            BurpExtender.this.stdout.println("启动 空格url编码");
                            BurpExtender.this.diy_payload_1 = 1;
                            String temp_data = jta.getText();
                            temp_data = temp_data.replaceAll(" ", "%20");
                            BurpExtender.this.JTextArea_data_1 = temp_data;
                        } else {
                            BurpExtender.this.stdout.println("关闭 空格url编码");
                            BurpExtender.this.diy_payload_1 = 0;
                            BurpExtender.this.JTextArea_data_1 = jta.getText();
                        }

                    }
                });
                chkbox7.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (chkbox7.isSelected()) {
                            BurpExtender.this.stdout.println("启动 自定义payload参数值置空");
                            BurpExtender.this.diy_payload_2 = 1;
                        } else {
                            BurpExtender.this.stdout.println("关闭 自定义payload参数值置空");
                            BurpExtender.this.diy_payload_2 = 0;
                        }

                    }
                });
                chkbox8.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (chkbox8.isSelected()) {
                            BurpExtender.this.stdout.println("启动 测试Cookie");
                            BurpExtender.this.is_cookie = 2;
                        } else {
                            BurpExtender.this.stdout.println("关闭 测试Cookie");
                            BurpExtender.this.is_cookie = -1;
                        }

                    }
                });
                btn1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        BurpExtender.this.log.clear();
                        BurpExtender.this.log2.clear();
                        BurpExtender.this.log3.clear();
                        BurpExtender.this.log4_md5.clear();
                        BurpExtender.this.conut = 0;
                        BurpExtender.this.fireTableRowsInserted(BurpExtender.this.log.size(), BurpExtender.this.log.size());
                        BurpExtender.this.model.fireTableRowsInserted(BurpExtender.this.log3.size(), BurpExtender.this.log3.size());
                    }
                });
                btn2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (BurpExtender.this.diy_payload_1 == 1) {
                            String temp_data = jta.getText();
                            temp_data = temp_data.replaceAll(" ", "%20");
                            BurpExtender.this.JTextArea_data_1 = temp_data;
                        } else {
                            BurpExtender.this.JTextArea_data_1 = jta.getText();
                        }

                        try {
                            BufferedWriter out = new BufferedWriter(new FileWriter("PFuzzPlus_diy_payload.ini"));
                            out.write(BurpExtender.this.JTextArea_data_1);
                            out.close();
                        } catch (IOException var3) {
                        }

                    }
                });
                btn3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (btn3.getText().equals("启动白名单")) {
                            btn3.setText("关闭白名单");
                            BurpExtender.this.white_URL = textField.getText();
                            BurpExtender.this.white_switchs = 1;
                            textField.setEditable(false);
                            textField.setForeground(Color.GRAY);
                        } else {
                            btn3.setText("启动白名单");
                            BurpExtender.this.white_switchs = 0;
                            textField.setEditable(true);
                            textField.setForeground(Color.BLACK);
                        }

                    }
                });
                log_btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        BurpExtender.this.log_text.setText("");
                    }
                });
                chkbox_diy_error.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (chkbox_diy_error.isSelected()) {
                            BurpExtender.this.diy_error_text = diy_error_jta.getText();
                            diy_error_jta.setEditable(false);
                            diy_error_jta.setBackground(Color.LIGHT_GRAY);
                        } else {
                            diy_error_jta.setEditable(true);
                            diy_error_jta.setBackground(Color.WHITE);
                        }

                    }
                });
                jps.add(jls);
                jps.add(jls_2);
                jps.add(chkbox1);
                jps.add(chkbox2);
                jps.add(chkbox3);
                jps.add(chkbox4);
                jps.add(chkbox8);
                jps.add(btn1);
                jps.add(jls_5);
                jps.add(textField);
                jps.add(btn3);
                JTabbedPane tabs = new JTabbedPane();
                BurpExtender.this.requestViewer = callbacks.createMessageEditor(BurpExtender.this, false);
                BurpExtender.this.responseViewer = callbacks.createMessageEditor(BurpExtender.this, false);
                tabs.addTab("Request", BurpExtender.this.requestViewer.getComponent());
                tabs.addTab("Response", BurpExtender.this.responseViewer.getComponent());
                splitPanes_2.setLeftComponent(jps);
                splitPanes_2.setRightComponent(tab_diy);
                splitPanes.setLeftComponent(jp);
                splitPanes.setRightComponent(tabs);
                BurpExtender.this.splitPane.setLeftComponent(splitPanes);
                BurpExtender.this.splitPane.setRightComponent(splitPanes_2);
                BurpExtender.this.splitPane.setDividerLocation(1000);
                callbacks.customizeUiComponent(BurpExtender.this.splitPane);
                callbacks.customizeUiComponent(BurpExtender.this.logTable);
                callbacks.customizeUiComponent(scrollPane);
                callbacks.customizeUiComponent(pane);
                callbacks.customizeUiComponent(jps);
                callbacks.customizeUiComponent(jp);
                callbacks.customizeUiComponent(tabs);
                callbacks.addSuiteTab(BurpExtender.this);
                callbacks.registerHttpListener(BurpExtender.this);
                callbacks.registerScannerCheck(BurpExtender.this);
                callbacks.registerContextMenuFactory(BurpExtender.this);
            }
        });
    }

    public String getTabCaption() {
        return "PFuzzPlus";
    }

    public Component getUiComponent() {
        return this.splitPane;
    }

    public void processHttpMessage(final int toolFlag, boolean messageIsRequest, final IHttpRequestResponse messageInfo) {
        if (this.switchs == 1 && (toolFlag == this.clicks_Repeater || toolFlag == this.clicks_Proxy) && !messageIsRequest) {
            synchronized(this.log) {
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            BurpExtender.this.checkVul(messageInfo, toolFlag);
                        } catch (Exception var2) {
                            var2.printStackTrace();
                            BurpExtender.this.stdout.println(var2);
                        }

                    }
                });
                thread.start();
            }
        }

    }

    public List<IScanIssue> doPassiveScan(IHttpRequestResponse baseRequestResponse) {
        return null;
    }

    public List<JMenuItem> createMenuItems(IContextMenuInvocation invocation) {
        List<JMenuItem> listMenuItems = new ArrayList(1);
        if (invocation.getToolFlag() == 64 || invocation.getToolFlag() == 4) {
            final IHttpRequestResponse[] responses = invocation.getSelectedMessages();
            JMenuItem jMenu = new JMenuItem("Send to PFuzzPlus");
            jMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (BurpExtender.this.switchs == 1) {
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    BurpExtender.this.checkVul(responses[0], 1024);
                                } catch (Exception var2) {
                                    var2.printStackTrace();
                                    BurpExtender.this.stdout.println(var2);
                                }

                            }
                        });
                        thread.start();
                    } else {
                        BurpExtender.this.stdout.println("插件PFuzzPlus关闭状态！");
                    }

                }
            });
            listMenuItems.add(jMenu);
        }

        return listMenuItems;
    }

    private void checkVul(IHttpRequestResponse baseRequestResponse, int toolFlag) {
        String change_sign_1 = "";
        String error_sign = "";
        List<IParameter> paraLists = this.helpers.analyzeRequest(baseRequestResponse).getParameters();
        this.temp_data = String.valueOf(this.helpers.analyzeRequest(baseRequestResponse).getUrl());
        String[] temp_data_strarray = this.temp_data.split("\\?");
        String temp_data = temp_data_strarray[0];
        String[] white_URL_list = this.white_URL.split(",");
        boolean white_swith = false;
        if (this.white_switchs == 1) {
            white_swith = false;

            for(int i = 0; i < white_URL_list.length; ++i) {
                if (temp_data.contains(white_URL_list[i])) {
                    this.stdout.println("白名单URL！" + temp_data);
                    white_swith = true;
                }
            }

            if (!white_swith) {
                this.stdout.println("不是白名单URL！" + temp_data);
                return;
            }
        }

        String[] request_datas;
        int json_count;
        int i;
        if (toolFlag == 4 || toolFlag == 64) {
            String[] static_file = new String[]{"jpg", "png", "gif", "css", "js", "pdf", "mp3", "mp4", "avi"};
            request_datas = temp_data.split("\\.");
            String static_file_2 = request_datas[request_datas.length - 1];
            String[] var14 = static_file;
            json_count = static_file.length;

            for(int var16 = 0; var16 < json_count; ++var16) {
                String i2 = var14[var16];
                if (static_file_2.equals(i2)) {
                    this.stdout.println("当前url为静态文件：" + temp_data + "\n");
                    return;
                }
            }

            IResponseInfo responseInfo = this.callbacks.getHelpers().analyzeResponse(baseRequestResponse.getResponse());
            byte[] responseBody = Arrays.copyOfRange(baseRequestResponse.getResponse(), responseInfo.getBodyOffset(), baseRequestResponse.getResponse().length);
            InputStream inputStream = new ByteArrayInputStream(responseBody);
            i = 0;
            byte[] buffer = new byte[8];

            try {
                i = inputStream.read(buffer);
            } catch (IOException var38) {
                var38.printStackTrace();
            }

            if (i >= 2 && buffer[0] == -1 && buffer[1] == -40) {
                this.stdout.println("当前url的响应包为jpg图片：" + temp_data + "\n");
                return;
            }

            if (i >= 4 && buffer[0] == -119 && buffer[1] == 80 && buffer[2] == 78 && buffer[3] == 71) {
                this.stdout.println("当前url的响应包为png图片：" + temp_data + "\n");
                return;
            }

            if (i >= 2 && buffer[0] == 71 && buffer[1] == 73) {
                this.stdout.println("当前url的响应包为gif图片：" + temp_data + "\n");
                return;
            }

            System.out.println("返回的不是图片");
        }

        String request_data = null;
        int is_add = 0;
        Iterator var42 = paraLists.iterator();

        while(true) {
            IParameter para;
            do {
                if (!var42.hasNext()) {
                    temp_data = temp_data + "+" + this.helpers.analyzeRequest(baseRequestResponse).getMethod();
                    this.stdout.println("\nMD5(\"" + temp_data + "\")");
                    temp_data = MD5(temp_data);
                    this.stdout.println(temp_data);
                    var42 = this.log4_md5.iterator();

                    while(var42.hasNext()) {
                        Request_md5 i2 = (Request_md5)var42.next();
                        if (i2.md5_data.equals(temp_data)) {
                            if (toolFlag != 1024) {
                                return;
                            }

                            temp_data = String.valueOf(System.currentTimeMillis());
                            this.stdout.println(temp_data);
                            temp_data = MD5(temp_data);
                            this.stdout.println(temp_data);
                        }
                    }

                    if (is_add != 0) {
                        this.log4_md5.add(new Request_md5(temp_data));
                        this.stdout.println(is_add);
                        this.stdout.println(request_data);
                        int row = this.log.size();

                        try {
                            this.original_data_len = this.callbacks.saveBuffersToTempFiles(baseRequestResponse).getResponse().length;
                            this.stdout.println(this.original_data_len);
                            if (this.original_data_len <= 0) {
                                this.stdout.println("该数据包无响应");
                                return;
                            }
                        } catch (Exception var39) {
                            this.stdout.println("该数据包无响应");
                            return;
                        }

                        this.log.add(new LogEntry(this.conut, toolFlag, this.callbacks.saveBuffersToTempFiles(baseRequestResponse), this.helpers.analyzeRequest(baseRequestResponse).getUrl(), "", "", "", temp_data, 0, "run……", 999));
                        ++this.conut;
                        this.fireTableRowsInserted(row, row);
                    }

                    List<IParameter> paraList = this.helpers.analyzeRequest(baseRequestResponse).getParameters();
                    byte[] new_Request = baseRequestResponse.getRequest();
                    json_count = -1;
                    String para_name = "";
                    Iterator var54 = paraList.iterator();

                    while(var54.hasNext()) {
                        IParameter para2 = (IParameter)var54.next();
                        boolean switch_para = false;
                        if (para2.getType() == 6) {
                            ++json_count;
                        }

                        ArrayList<String> payloads = new ArrayList();
                        if (diy_payload_2 != 1){
                            payloads.add("'");
                            payloads.add("''");
                        }

                        if (para2.getType() == 0 || para2.getType() == 1 || para2.getType() == 6 || para2.getType() == this.is_cookie) {
                            String key = para2.getName();
                            String value = para2.getValue();
                            this.stdout.println("\n\n原始数据：" + key + ":" + value);
                            int time_1;
                            if (this.JTextArea_int == 1) {
                                String[] JTextArea_data = this.JTextArea_data_1.split("\n");
                                payloads.clear();
                                String[] var24 = JTextArea_data;
                                int var25 = JTextArea_data.length;

                                for(time_1 = 0; time_1 < var25; ++time_1) {
                                    String a = var24[time_1];
                                    payloads.add(a);
                                }
                            }

                            if (this.is_int == 1 && value.matches("[0-9]+")) {
                                payloads.add("-1");
                                payloads.add("-0");
                            }

                            int change = 0;

                            IHttpRequestResponse requestResponse;
                            String payload;
                            int time_2;
                            String change_sign;
                            for(Iterator var57 = payloads.iterator(); var57.hasNext(); this.log2.add(new LogEntry(this.conut, toolFlag, this.callbacks.saveBuffersToTempFiles(requestResponse), this.helpers.analyzeRequest(requestResponse).getUrl(), key, value + payload, change_sign, temp_data, time_2 - time_1, "end", this.helpers.analyzeResponse(requestResponse.getResponse()).getStatusCode()))) {
                                payload = (String)var57.next();
                                time_1 = 0;
                                time_2 = 0;
//                                if (this.JTextArea_int == 1 && this.diy_payload_2 == 1 && payload != "'" && payload != "''" && payload != "-1" && payload != "-0") {
                                if (this.JTextArea_int == 1 && this.diy_payload_2 == 1) {
                                    value = "";
                                }

                                IHttpService iHttpService = baseRequestResponse.getHttpService();
                                requestResponse = null;
                                String[] request_data_temp;
                                Pattern p;
                                if (para2.getType() != 6) {
                                    this.stdout.println("普通格式");
                                    IParameter newPara = this.helpers.buildParameter(key, value + payload, para2.getType());
                                    byte[] newRequest = this.helpers.updateParameter(new_Request, newPara);
                                    time_1 = (int)System.currentTimeMillis();
                                    requestResponse = this.callbacks.makeHttpRequest(iHttpService, newRequest);
                                    time_2 = (int)System.currentTimeMillis();
                                } else {
                                    List<String> headers = this.helpers.analyzeRequest(baseRequestResponse).getHeaders();
                                    if (is_add == 1) {
                                        this.stdout.println("json");
                                        String newBody = "{";
                                        Iterator var64 = paraList.iterator();

                                        label261:
                                        while(true) {
                                            while(true) {
                                                IParameter paras;
                                                do {
                                                    if (!var64.hasNext()) {
                                                        newBody = newBody.substring(0, newBody.length() - 1);
                                                        newBody = newBody + "}";
                                                        byte[] bodyByte = newBody.getBytes();
                                                        byte[] new_Requests = this.helpers.buildHttpMessage(headers, bodyByte);
                                                        time_1 = (int)System.currentTimeMillis();
                                                        requestResponse = this.callbacks.makeHttpRequest(iHttpService, new_Requests);
                                                        time_2 = (int)System.currentTimeMillis();
                                                        break label261;
                                                    }

                                                    paras = (IParameter)var64.next();
                                                } while(paras.getType() != 6);

                                                if (key == paras.getName() && value == paras.getValue()) {
                                                    newBody = newBody + "\"" + paras.getName() + "\":\"" + paras.getValue() + payload + "\",";
                                                } else {
                                                    if (diy_payload_2 == 1){
                                                        if (key == paras.getName()){
                                                            newBody += "\"" + paras.getName() + "\":\"" + value + payload + "\",";//构造json的body
                                                        }else{
                                                            newBody += "\"" + paras.getName() + "\":\"" + paras.getValue() + "\",";//构造json的body
                                                        }
                                                    }else{
                                                        newBody += "\"" + paras.getName() + "\":\"" + value + "\",";//构造json的body
                                                    }
//                                                    newBody = newBody + "\"" + paras.getName() + "\":\"" + paras.getValue() + "\",";
                                                }
                                            }
                                        }
                                    } else if (is_add == 2) {
                                        request_data = request_data.replaceAll("\r", "");
                                        request_data = request_data.replaceAll("\n", "");
                                        request_data_temp = request_data.split(",\"");
                                        String request_data_body = "";
                                        String request_data_body_temp = "";

                                        for(int i2 = 0; i2 < request_data_temp.length; ++i2) {
                                            if (i2 != json_count) {
                                                request_data_body = request_data_body + "\"" + request_data_temp[i2] + ",";
                                            } else {
                                                request_data_body_temp = request_data_temp[i2];
                                                this.stdout.println("准备修改的值：" + request_data_body_temp);

                                                while(request_data_body_temp.contains(":[]")) {
                                                    this.stdout.println(request_data_body_temp + "跳过");
                                                    request_data_body = request_data_body + "\"" + request_data_temp[i2] + ",";
                                                    ++json_count;
                                                    ++i2;
                                                    request_data_body_temp = request_data_temp[i2];
                                                }

                                                if (request_data_body_temp.toLowerCase().contains(":null") || request_data_body_temp.toLowerCase().contains(":true") || request_data_body_temp.toLowerCase().contains(":false")) {
                                                    this.stdout.println(request_data_body_temp + "跳过");
                                                    switch_para = true;
                                                    break;
                                                }

                                                if (!request_data_body_temp.contains("\":")) {
                                                    this.stdout.println("处理过，无需处理");
                                                    switch_para = true;
                                                    if (para2.getName().equals(para_name)) {
                                                        this.stdout.println("json嵌套列表，已经处理过第一个值");
                                                    }
                                                    break;
                                                }

                                                if (para2.getName().equals(para_name)) {
                                                    this.stdout.println("json嵌套列表，这个参数处理过了，跳过");
                                                    --json_count;
                                                    switch_para = true;
                                                    break;
                                                }

                                                p = Pattern.compile(".*:\\s?\\[?\\s?(.*?$)");
                                                Matcher m = p.matcher(request_data_body_temp);
                                                if (m.find()) {
                                                    request_data_body_temp = m.group(1);
                                                }

                                                if (request_data_body_temp.contains("\"")) {
                                                    request_data_body_temp = request_data_temp[i2];
                                                    if (diy_payload_2 == 1){
                                                        request_data_body_temp = request_data_body_temp.replaceAll("^(.*:.*?\")(.*?)(\"[^\"]*)$", "$1" + payload + "$3");
                                                    }else{
                                                        request_data_body_temp = request_data_body_temp.replaceAll("^(.*:.*?\")(.*?)(\"[^\"]*)$", "$1$2" + payload + "$3");
                                                    }
                                                    this.stdout.println(request_data_body_temp);
                                                    request_data_body = request_data_body + "\"" + request_data_body_temp + ",";
                                                } else {
                                                    request_data_body_temp = request_data_temp[i2];
                                                    if (diy_payload_2 == 1){
                                                        request_data_body_temp = request_data_body_temp.replaceAll("^(.*:.*?)(\\d*)([^\"\\d]*)$", "$1\"" + payload + "\"$3");
                                                    }else{
                                                        request_data_body_temp = request_data_body_temp.replaceAll("^(.*:.*?)(\\d*)([^\"\\d]*)$", "$1\"$2" + payload + "\"$3");
                                                    }
                                                    this.stdout.println(request_data_body_temp);
                                                    request_data_body = request_data_body + "\"" + request_data_body_temp + ",";
                                                }
                                            }
                                        }

                                        if (switch_para) {
                                            break;
                                        }

                                        if (para2.getName().equals(para_name)) {
                                            this.stdout.println("json嵌套列表，已经处理过第一个值!!!!");
                                        }

                                        request_data_body = request_data_body.substring(0, request_data_body.length() - 1);
                                        request_data_body = request_data_body.substring(1, request_data_body.length());
                                        byte[] bodyByte = request_data_body.getBytes();
                                        byte[] new_Requests = this.helpers.buildHttpMessage(headers, bodyByte);
                                        time_1 = (int)System.currentTimeMillis();
                                        requestResponse = this.callbacks.makeHttpRequest(iHttpService, new_Requests);
                                        time_2 = (int)System.currentTimeMillis();
                                    }
                                }

                                if (payload != "'" && payload != "-1" && change != 0) {
                                    if (payload != "''" && payload != "-0") {
                                        if (time_2 - time_1 >= 3000) {
                                            change_sign = "time > 3";
                                            change_sign_1 = " ✔";
                                        } else {
                                            change_sign = "diy payload";
                                        }
                                    } else if (change == requestResponse.getResponse().length) {
                                        change_sign = "";
                                    } else if ((payload != "''" || requestResponse.getResponse().length != this.original_data_len) && (payload != "-0" || requestResponse.getResponse().length != this.original_data_len)) {
                                        int var10000 = change - requestResponse.getResponse().length;
                                        change_sign = "✔ " + var10000;
                                        change_sign_1 = " ✔";
                                    } else {
                                        change_sign = "✔ ==> ?";
                                        change_sign_1 = " ✔";
                                    }
                                } else {
                                    change = requestResponse.getResponse().length;
                                    change_sign = "";
                                }

                                request_data_temp = this.diy_error_text.split("\n");
                                int var66 = request_data_temp.length;

                                for(int var72 = 0; var72 < var66; ++var72) {
                                    String keyword = request_data_temp[var72];
                                    p = Pattern.compile(keyword);
                                    if (p.matcher(this.helpers.bytesToString(requestResponse.getResponse())).find()) {
                                        error_sign = " Err";
                                        change_sign = change_sign + " Err";
                                        this.log_text.insert(String.valueOf(this.conut - 1) + "-->diy_error:" + keyword + "\n", 0);
                                        break;
                                    }
                                }
                            }
                        }

                        para_name = para2.getName();
                        this.stdout.println(json_count);
                    }

                    for(i = 0; i < this.log.size(); ++i) {
                        if (temp_data.equals(((LogEntry)this.log.get(i)).data_md5)) {
                            ((LogEntry)this.log.get(i)).setState("end!" + change_sign_1 + error_sign);
                        }
                    }

                    this.fireTableDataChanged();
                    this.logTable.setRowSelectionInterval(this.select_row, this.select_row);
                    return;
                }

                para = (IParameter)var42.next();
            } while(para.getType() != 0 && para.getType() != 1 && para.getType() != 6 && para.getType() != this.is_cookie);

            if (is_add == 0) {
                is_add = 1;
            }

            if (para.getType() == 1) {
                String pattern = "^[a-zA-Z\\s\\d\\.~!@#$%\\^&\\*\\(\\)-_=+\\\\\\{\\}\\]:;\"',/\\?]{1,}$";
                if (!para.getName().matches(pattern)) {
                    this.stdout.println("当前url的body为二进制：" + temp_data + "\n");
                    return;
                }
            }

            temp_data = temp_data + "+" + para.getName();
            if (para.getType() == 6 && request_data == null) {
                try {
                    request_data = this.helpers.bytesToString(baseRequestResponse.getRequest()).split("\r\n\r\n")[1];
                    request_datas = request_data.split("\\{");
                    if (request_datas.length > 2) {
                        is_add = 2;
                    }

                    request_datas = request_data.split("\":\\[");
                    if (request_datas.length > 1) {
                        is_add = 2;
                    }
                } catch (Exception var37) {
                    this.stdout.println(var37);
                }
            }
        }
    }

    public List<IScanIssue> doActiveScan(IHttpRequestResponse baseRequestResponse, IScannerInsertionPoint insertionPoint) {
        return null;
    }

    public int consolidateDuplicateIssues(IScanIssue existingIssue, IScanIssue newIssue) {
        return existingIssue.getIssueName().equals(newIssue.getIssueName()) ? -1 : 0;
    }

    public int getRowCount() {
        return this.log.size();
    }

    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "#";
            case 1:
                return "来源";
            case 2:
                return "URL";
            case 3:
                return "返回包长度";
            case 4:
                return "状态";
            default:
                return "";
        }
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        LogEntry logEntry = (LogEntry)this.log.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return logEntry.id;
            case 1:
                return this.callbacks.getToolName(logEntry.tool);
            case 2:
                return logEntry.url.toString();
            case 3:
                return logEntry.requestResponse.getResponse().length;
            case 4:
                return logEntry.state;
            default:
                return "";
        }
    }

    public byte[] getRequest() {
        return this.currentlyDisplayedItem.getRequest();
    }

    public byte[] getResponse() {
        return this.currentlyDisplayedItem.getResponse();
    }

    public IHttpService getHttpService() {
        return this.currentlyDisplayedItem.getHttpService();
    }

    public static String MD5(String key) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = key.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            return null;
        }
    }

    class MyModel extends AbstractTableModel {
        MyModel() {
        }

        public int getRowCount() {
            return BurpExtender.this.log3.size();
        }

        public int getColumnCount() {
            return 6;
        }

        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "参数";
                case 1:
                    return "payload";
                case 2:
                    return "返回包长度";
                case 3:
                    return "变化";
                case 4:
                    return "用时";
                case 5:
                    return "响应码";
                default:
                    return "";
            }
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            LogEntry logEntry2 = (LogEntry)BurpExtender.this.log3.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return logEntry2.parameter;
                case 1:
                    return logEntry2.value;
                case 2:
                    return logEntry2.requestResponse.getResponse().length;
                case 3:
                    return logEntry2.change;
                case 4:
                    return logEntry2.times;
                case 5:
                    return logEntry2.response_code;
                default:
                    return "";
            }
        }
    }

    private static class Request_md5 {
        final String md5_data;

        Request_md5(String md5_data) {
            this.md5_data = md5_data;
        }
    }

    private static class LogEntry {
        final int id;
        final int tool;
        final IHttpRequestResponsePersisted requestResponse;
        final URL url;
        final String parameter;
        final String value;
        final String change;
        final String data_md5;
        final int times;
        final int response_code;
        String state;

        LogEntry(int id, int tool, IHttpRequestResponsePersisted requestResponse, URL url, String parameter, String value, String change, String data_md5, int times, String state, int response_code) {
            this.id = id;
            this.tool = tool;
            this.requestResponse = requestResponse;
            this.url = url;
            this.parameter = parameter;
            this.value = value;
            this.change = change;
            this.data_md5 = data_md5;
            this.times = times;
            this.state = state;
            this.response_code = response_code;
        }

        public String setState(String state) {
            this.state = state;
            return this.state;
        }
    }

    private class Table extends JTable {
        public Table(TableModel tableModel) {
            super(tableModel);
        }

        public void changeSelection(int row, int col, boolean toggle, boolean extend) {
            LogEntry logEntry = (LogEntry)BurpExtender.this.log.get(row);
            BurpExtender.this.data_md5_id = logEntry.data_md5;
            BurpExtender.this.select_row = logEntry.id;
            BurpExtender.this.log3.clear();

            for(int i = 0; i < BurpExtender.this.log2.size(); ++i) {
                if (((LogEntry)BurpExtender.this.log2.get(i)).data_md5 == BurpExtender.this.data_md5_id) {
                    BurpExtender.this.log3.add((LogEntry)BurpExtender.this.log2.get(i));
                }
            }

            BurpExtender.this.model.fireTableRowsInserted(BurpExtender.this.log3.size(), BurpExtender.this.log3.size());
            BurpExtender.this.model.fireTableDataChanged();
            BurpExtender.this.requestViewer.setMessage(logEntry.requestResponse.getRequest(), true);
            BurpExtender.this.responseViewer.setMessage(logEntry.requestResponse.getResponse(), false);
            BurpExtender.this.currentlyDisplayedItem = logEntry.requestResponse;
            super.changeSelection(row, col, toggle, extend);
        }
    }

    private class Table_log2 extends JTable {
        public Table_log2(TableModel tableModel) {
            super(tableModel);
        }

        public void changeSelection(int row, int col, boolean toggle, boolean extend) {
            LogEntry logEntry = (LogEntry)BurpExtender.this.log3.get(row);
            BurpExtender.this.requestViewer.setMessage(logEntry.requestResponse.getRequest(), true);
            BurpExtender.this.responseViewer.setMessage(logEntry.requestResponse.getResponse(), false);
            BurpExtender.this.currentlyDisplayedItem = logEntry.requestResponse;
            super.changeSelection(row, col, toggle, extend);
        }
    }
}
