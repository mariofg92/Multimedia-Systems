/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BandCombineOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.media.Buffer;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import pfinal.clasesFormas.FormaRectangularMFG;
import sm.image.ThresholdOp;


/**
 * Clase de la ventana principal del programa.
 * @author Mario
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    private BufferedImage imagen_a_mostrar = null;
    private ArrayList<BotonColor> botones_color = new ArrayList();
    private BotonColor boton_color_frente = new BotonColor(Color.BLACK);
    private BotonColor boton_color_fondo = new BotonColor(Color.WHITE);
    private VentanaGrabacion vg = new VentanaGrabacion();
    private VentanaCamara vc = null;

    
    /**
     * Constructor de la clase. Crea la ventana, añade la ventana de grabación, los botones de los
     * diferentes colores con sus correspondientes Listeners, los botones de color de frente y fondo
     * y el panel que engloba a todos estos botones.
     */
    public VentanaPrincipal() {
        initComponents();
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        escritorio.add(vg);
        
        toolBarDibujo.setLayout(new javax.swing.BoxLayout(toolBarDibujo, BoxLayout.Y_AXIS));
        
        botones_color.add(new BotonColor(Color.BLACK));
        botones_color.add(new BotonColor(Color.WHITE));
        botones_color.add(new BotonColor(Color.GREEN));
        botones_color.add(new BotonColor(Color.YELLOW));
        botones_color.add(new BotonColor(Color.RED));
        botones_color.add(new BotonColor(Color.LIGHT_GRAY));
        botones_color.add(new BotonColor(Color.BLUE));
        botones_color.add(new BotonColor(Color.CYAN));
        botones_color.add(new BotonColor(Color.DARK_GRAY));
        botones_color.add(new BotonColor(Color.MAGENTA));
        
        grupoFrenteFondo.add(boton_color_frente);
        grupoFrenteFondo.add(boton_color_fondo);
        panelColorFrente.add(boton_color_frente);
        panelColorFondo.add(boton_color_fondo);
        boton_color_frente.setSelected(true);
        boton_color_frente.setToolTipText("Color de trazo");
        //boton_color_frente.setText("Frente");
        boton_color_fondo.setToolTipText("Color de relleno");
        //boton_color_fondo.setText("Fondo");

        for(BotonColor b: botones_color){
            panelTodosColores.add(b);
            
            b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                if(boton_color_frente.isSelected()){
                    boton_color_frente.setColor(((BotonColor)evt.getSource()).getColor());
                    
                    Lienzo.setColorTrazo(boton_color_frente.getColor());
                        
                    if(Lienzo.getEditar() && Lienzo.getFormaAEditar() != null){
                        Lienzo.getFormaAEditar().setColorTrazo(boton_color_frente.getColor());
                        Lienzo.setColorTrazo(boton_color_frente.getColor());
                        VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                        vi.getLienzo().repaint();
                    }
                }else if(boton_color_fondo.isSelected()){
                    boton_color_fondo.setColor(((BotonColor)evt.getSource()).getColor());
                        
                    Lienzo.setColorFondo(boton_color_fondo.getColor());
                               
                    if(Lienzo.getEditar() && Lienzo.getFormaAEditar() != null && (Lienzo.getFormaAEditar() instanceof FormaRectangularMFG)){
                        ((FormaRectangularMFG)Lienzo.getFormaAEditar()).setPaintRelleno(boton_color_fondo.getColor());
                        ((FormaRectangularMFG)Lienzo.getFormaAEditar()).setRelleno(checkRelleno.isSelected());
                        VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                        vi.getLienzo().repaint();
                    }
                    
                }
                
                ((BotonColor)evt.getSource()).setSelected(false);
            }
            });
            
        }
        panelTodosColores.add(botonMasColores);
        
        boton_color_fondo.setEnabled(false);
        
                
        
        repaint();
    }
    
    
    /**
     * Función que convierte BufferdImage de un tipo a otro, por ejemplo al tipo RGB.
     * @param img Imagen a convertir.
     * @param type Tipo de imagen al que convertir.
     * @return Devuelve una imagen resultado de convertir img al tipo indicado.
     */
    BufferedImage convertImageType(BufferedImage img, int type){ 
        if(img==null) 
            return null; 
        BufferedImage imgOut = new BufferedImage(img.getWidth(), img.getHeight(), type); 
        Graphics2D g2d = imgOut.createGraphics(); 
        g2d.drawImage(img,0,0,null); 
        return imgOut; 
    } 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoFormas = new javax.swing.ButtonGroup();
        grupoFrenteFondo = new javax.swing.ButtonGroup();
        lienzo1 = new pfinal.Lienzo();
        dialogoAcercaDe = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        labelTexto = new javax.swing.JLabel();
        frameColorChooser = new javax.swing.JFrame();
        colorChooser = new javax.swing.JColorChooser();
        botonAñadirColor = new javax.swing.JButton();
        frameSizeChooser = new javax.swing.JFrame();
        panelTodo = new javax.swing.JPanel();
        labelAltura = new javax.swing.JLabel();
        textFieldAltura = new javax.swing.JTextField();
        labelAnchura = new javax.swing.JLabel();
        textFieldAnchura = new javax.swing.JTextField();
        botonAceptar = new javax.swing.JButton();
        labelHerramientaSelec = new javax.swing.JLabel();
        panelPrincipal = new javax.swing.JPanel();
        barraGeneral = new javax.swing.JToolBar();
        botonNuevo = new javax.swing.JButton();
        botonAbrir = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        botonWebcam = new javax.swing.JButton();
        botonCapturar = new javax.swing.JButton();
        botonGrabar = new javax.swing.JButton();
        escritorio = new javax.swing.JDesktopPane();
        toolBarImagen = new javax.swing.JToolBar();
        panelBrillo = new javax.swing.JPanel();
        sliderBrillo = new javax.swing.JSlider();
        panelFiltro = new javax.swing.JPanel();
        comboBoxFiltro = new javax.swing.JComboBox();
        panelContraste = new javax.swing.JPanel();
        botonContraste = new javax.swing.JButton();
        botonIluminar = new javax.swing.JButton();
        botonOscurecer = new javax.swing.JButton();
        panelRotacion = new javax.swing.JPanel();
        sliderGiro = new javax.swing.JSlider();
        botonGiro90 = new javax.swing.JButton();
        botonGiro180 = new javax.swing.JButton();
        botonGiro270 = new javax.swing.JButton();
        panelEscala = new javax.swing.JPanel();
        botonAumentarEscala = new javax.swing.JButton();
        botonDisminuirEscala = new javax.swing.JButton();
        tabPanel = new javax.swing.JTabbedPane();
        panelDuplicar = new javax.swing.JPanel();
        botonDuplicar = new javax.swing.JButton();
        botonNegativo = new javax.swing.JButton();
        botonEscalaGrises = new javax.swing.JButton();
        comboBoxBandSelector = new javax.swing.JComboBox();
        panelUmbral = new javax.swing.JPanel();
        sliderUmbral = new javax.swing.JSlider();
        toolBarDibujo = new javax.swing.JToolBar();
        panelFormas = new javax.swing.JPanel();
        botonPunto = new javax.swing.JToggleButton();
        botonLinea = new javax.swing.JToggleButton();
        botonRectangulo = new javax.swing.JToggleButton();
        botonOvalo = new javax.swing.JToggleButton();
        botonCurva1pto = new javax.swing.JToggleButton();
        panelGrosor = new javax.swing.JPanel();
        comboBoxStroke = new javax.swing.JComboBox();
        spinGrosor = new javax.swing.JSpinner();
        panelColores = new javax.swing.JPanel();
        panelFrenteFondo = new javax.swing.JPanel();
        panelColorFrente = new javax.swing.JPanel();
        panelColorFondo = new javax.swing.JPanel();
        panelTodosColores = new javax.swing.JPanel();
        botonMasColores = new javax.swing.JButton();
        panelRellenoEditar = new javax.swing.JPanel();
        checkRelleno = new javax.swing.JCheckBox();
        checkEditar = new javax.swing.JCheckBox();
        labelTipoRelleno = new javax.swing.JLabel();
        comboBoxTipoRelleno = new javax.swing.JComboBox();
        barraMenu = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuNuevo = new javax.swing.JMenuItem();
        menuAbrir = new javax.swing.JMenuItem();
        menuGuardar = new javax.swing.JMenuItem();
        menuVer = new javax.swing.JMenu();
        menuBarraEstado = new javax.swing.JCheckBoxMenuItem();
        menuBarraGeneral = new javax.swing.JCheckBoxMenuItem();
        menuBarraDibujo = new javax.swing.JCheckBoxMenuItem();
        menuBarraImagen = new javax.swing.JCheckBoxMenuItem();
        menuAyuda = new javax.swing.JMenu();
        menuAcercaDe = new javax.swing.JMenuItem();

        org.jdesktop.layout.GroupLayout lienzo1Layout = new org.jdesktop.layout.GroupLayout(lienzo1);
        lienzo1.setLayout(lienzo1Layout);
        lienzo1Layout.setHorizontalGroup(
            lienzo1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        lienzo1Layout.setVerticalGroup(
            lienzo1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        dialogoAcercaDe.setTitle("Acerca de...");
        dialogoAcercaDe.setResizable(false);
        dialogoAcercaDe.setSize(new java.awt.Dimension(500, 250));
        dialogoAcercaDe.getContentPane().setLayout(new javax.swing.BoxLayout(dialogoAcercaDe.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        labelTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/banner.gif"))); // NOI18N
        labelTexto.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel3.add(labelTexto);

        dialogoAcercaDe.getContentPane().add(jPanel3);

        frameColorChooser.setSize(new java.awt.Dimension(700, 450));
        frameColorChooser.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        frameColorChooser.getContentPane().add(colorChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 370));

        botonAñadirColor.setBackground(new java.awt.Color(15, 165, 86));
        botonAñadirColor.setText("Añadir color seleccionado");
        botonAñadirColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAñadirColorActionPerformed(evt);
            }
        });
        frameColorChooser.getContentPane().add(botonAñadirColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, -1, -1));

        frameSizeChooser.setTitle("Tamaño nuevo");
        frameSizeChooser.setLocationByPlatform(true);
        frameSizeChooser.setSize(new java.awt.Dimension(195, 160));

        panelTodo.setSize(new java.awt.Dimension(191, 145));
        panelTodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelAltura.setText("Altura");
        panelTodo.add(labelAltura, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 21, -1, -1));

        textFieldAltura.setText("400");
        textFieldAltura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldAlturaActionPerformed(evt);
            }
        });
        panelTodo.add(textFieldAltura, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 15, 70, -1));

        labelAnchura.setText("Anchura");
        panelTodo.add(labelAnchura, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 55, -1, -1));

        textFieldAnchura.setText("600");
        panelTodo.add(textFieldAnchura, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 49, 70, -1));

        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });
        panelTodo.add(botonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 101, -1, -1));

        org.jdesktop.layout.GroupLayout frameSizeChooserLayout = new org.jdesktop.layout.GroupLayout(frameSizeChooser.getContentPane());
        frameSizeChooser.getContentPane().setLayout(frameSizeChooserLayout);
        frameSizeChooserLayout.setHorizontalGroup(
            frameSizeChooserLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelTodo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 191, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        frameSizeChooserLayout.setVerticalGroup(
            frameSizeChooserLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelTodo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelHerramientaSelec.setText("Herramienta: punto");
        getContentPane().add(labelHerramientaSelec, java.awt.BorderLayout.PAGE_END);

        panelPrincipal.setLayout(new java.awt.BorderLayout());

        barraGeneral.setRollover(true);

        botonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/NuevoBoceto.GIF"))); // NOI18N
        botonNuevo.setToolTipText("Nueva imagen");
        botonNuevo.setFocusable(false);
        botonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoActionPerformed(evt);
            }
        });
        barraGeneral.add(botonNuevo);

        botonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/abrir.png"))); // NOI18N
        botonAbrir.setToolTipText("Abrir (imagen, sonido, video)");
        botonAbrir.setFocusable(false);
        botonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrirActionPerformed(evt);
            }
        });
        barraGeneral.add(botonAbrir);

        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/Guardar.gif"))); // NOI18N
        botonGuardar.setToolTipText("Guardar imagen seleccionada");
        botonGuardar.setFocusable(false);
        botonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        barraGeneral.add(botonGuardar);
        barraGeneral.add(jSeparator1);

        botonWebcam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/webcam.png"))); // NOI18N
        botonWebcam.setToolTipText("Webcam");
        botonWebcam.setFocusable(false);
        botonWebcam.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonWebcam.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonWebcam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonWebcamActionPerformed(evt);
            }
        });
        barraGeneral.add(botonWebcam);

        botonCapturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/camara.png"))); // NOI18N
        botonCapturar.setToolTipText("Capturar fotograma video");
        botonCapturar.setFocusable(false);
        botonCapturar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCapturar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonCapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCapturarActionPerformed(evt);
            }
        });
        barraGeneral.add(botonCapturar);

        botonGrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/microfono.png"))); // NOI18N
        botonGrabar.setToolTipText("Grabar audio");
        botonGrabar.setFocusable(false);
        botonGrabar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGrabar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGrabarActionPerformed(evt);
            }
        });
        barraGeneral.add(botonGrabar);

        panelPrincipal.add(barraGeneral, java.awt.BorderLayout.NORTH);

        escritorio.setBackground(new java.awt.Color(51, 153, 0));
        escritorio.setForeground(new java.awt.Color(51, 51, 51));
        escritorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                escritorioMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                escritorioMouseEntered(evt);
            }
        });
        escritorio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                escritorioFocusLost(evt);
            }
        });
        panelPrincipal.add(escritorio, java.awt.BorderLayout.CENTER);

        toolBarImagen.setRollover(true);

        panelBrillo.setBorder(javax.swing.BorderFactory.createTitledBorder("Brillo"));
        panelBrillo.setLayout(new java.awt.BorderLayout());

        sliderBrillo.setMaximum(255);
        sliderBrillo.setMinimum(-255);
        sliderBrillo.setMinorTickSpacing(85);
        sliderBrillo.setPaintTicks(true);
        sliderBrillo.setToolTipText("Desliza para aumentar o disminuir el brillo.");
        sliderBrillo.setValue(0);
        sliderBrillo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderBrilloStateChanged(evt);
            }
        });
        sliderBrillo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sliderBrilloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                sliderBrilloFocusLost(evt);
            }
        });
        panelBrillo.add(sliderBrillo, java.awt.BorderLayout.CENTER);

        toolBarImagen.add(panelBrillo);

        panelFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));
        panelFiltro.setLayout(new java.awt.CardLayout());

        comboBoxFiltro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Emb. Media", "Emb. Binomial", "Enfoque", "Relieve", "Frontera" }));
        comboBoxFiltro.setToolTipText("Selecciona un filtro a aplicar.");
        comboBoxFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFiltroActionPerformed(evt);
            }
        });
        panelFiltro.add(comboBoxFiltro, "card2");

        toolBarImagen.add(panelFiltro);

        panelContraste.setBorder(javax.swing.BorderFactory.createTitledBorder("Contraste"));
        panelContraste.setLayout(new javax.swing.BoxLayout(panelContraste, javax.swing.BoxLayout.LINE_AXIS));

        botonContraste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/contraste.png"))); // NOI18N
        botonContraste.setToolTipText("Contraste.");
        botonContraste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonContrasteActionPerformed(evt);
            }
        });
        panelContraste.add(botonContraste);

        botonIluminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/iluminar.png"))); // NOI18N
        botonIluminar.setToolTipText("Iluminar.");
        botonIluminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIluminarActionPerformed(evt);
            }
        });
        panelContraste.add(botonIluminar);

        botonOscurecer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/oscurecer.png"))); // NOI18N
        botonOscurecer.setToolTipText("Oscurecer.");
        botonOscurecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOscurecerActionPerformed(evt);
            }
        });
        panelContraste.add(botonOscurecer);

        toolBarImagen.add(panelContraste);

        panelRotacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Rotación"));
        panelRotacion.setLayout(new javax.swing.BoxLayout(panelRotacion, javax.swing.BoxLayout.LINE_AXIS));

        sliderGiro.setMaximum(360);
        sliderGiro.setMinorTickSpacing(45);
        sliderGiro.setPaintTicks(true);
        sliderGiro.setToolTipText("Desliza para rotar.");
        sliderGiro.setValue(0);
        sliderGiro.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGiroStateChanged(evt);
            }
        });
        sliderGiro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sliderGiroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                sliderGiroFocusLost(evt);
            }
        });
        panelRotacion.add(sliderGiro);

        botonGiro90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/rotacion90.png"))); // NOI18N
        botonGiro90.setToolTipText("Rotación 90º.");
        botonGiro90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGiro90ActionPerformed(evt);
            }
        });
        panelRotacion.add(botonGiro90);

        botonGiro180.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/rotacion180.png"))); // NOI18N
        botonGiro180.setToolTipText("Rotación 180º.");
        botonGiro180.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGiro180ActionPerformed(evt);
            }
        });
        panelRotacion.add(botonGiro180);

        botonGiro270.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/rotacion270.png"))); // NOI18N
        botonGiro270.setToolTipText("Rotación 280º.");
        botonGiro270.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGiro270ActionPerformed(evt);
            }
        });
        panelRotacion.add(botonGiro270);

        toolBarImagen.add(panelRotacion);

        panelEscala.setBorder(javax.swing.BorderFactory.createTitledBorder("Escala"));
        panelEscala.setLayout(new javax.swing.BoxLayout(panelEscala, javax.swing.BoxLayout.LINE_AXIS));

        botonAumentarEscala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/aumentar.png"))); // NOI18N
        botonAumentarEscala.setToolTipText("Ampliar.");
        botonAumentarEscala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAumentarEscalaActionPerformed(evt);
            }
        });
        panelEscala.add(botonAumentarEscala);

        botonDisminuirEscala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/disminuir.png"))); // NOI18N
        botonDisminuirEscala.setToolTipText("Disminuir.");
        botonDisminuirEscala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDisminuirEscalaActionPerformed(evt);
            }
        });
        panelEscala.add(botonDisminuirEscala);

        toolBarImagen.add(panelEscala);

        tabPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        tabPanel.setName(""); // NOI18N

        panelDuplicar.setLayout(new javax.swing.BoxLayout(panelDuplicar, javax.swing.BoxLayout.LINE_AXIS));

        botonDuplicar.setText("Duplicar");
        botonDuplicar.setToolTipText("Duplicar.");
        botonDuplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDuplicarActionPerformed(evt);
            }
        });
        panelDuplicar.add(botonDuplicar);

        botonNegativo.setText("Negativo");
        botonNegativo.setToolTipText("Negativo.");
        botonNegativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNegativoActionPerformed(evt);
            }
        });
        panelDuplicar.add(botonNegativo);

        botonEscalaGrises.setText("Escala de Grises");
        botonEscalaGrises.setToolTipText("Escala de grises.");
        botonEscalaGrises.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEscalaGrisesActionPerformed(evt);
            }
        });
        panelDuplicar.add(botonEscalaGrises);

        comboBoxBandSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Banda R", "Banda G", "Banda B", "Banda R + G", "Banda R + B", "Banda G + B" }));
        comboBoxBandSelector.setToolTipText("Selector de banda RGB.");
        comboBoxBandSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxBandSelectorActionPerformed(evt);
            }
        });
        panelDuplicar.add(comboBoxBandSelector);

        tabPanel.addTab("tab1", panelDuplicar);

        panelUmbral.setToolTipText("");
        panelUmbral.setLayout(new javax.swing.BoxLayout(panelUmbral, javax.swing.BoxLayout.LINE_AXIS));

        sliderUmbral.setMaximum(255);
        sliderUmbral.setToolTipText("Umbralización");
        sliderUmbral.setValue(0);
        sliderUmbral.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderUmbralStateChanged(evt);
            }
        });
        sliderUmbral.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sliderUmbralFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                sliderUmbralFocusLost(evt);
            }
        });
        panelUmbral.add(sliderUmbral);

        tabPanel.addTab("tab2", panelUmbral);

        toolBarImagen.add(tabPanel);

        panelPrincipal.add(toolBarImagen, java.awt.BorderLayout.SOUTH);

        toolBarDibujo.setOrientation(javax.swing.SwingConstants.VERTICAL);
        toolBarDibujo.setRollover(true);

        panelFormas.setBorder(javax.swing.BorderFactory.createTitledBorder("Formas"));
        panelFormas.setLayout(new java.awt.GridLayout(0, 2));

        grupoFormas.add(botonPunto);
        botonPunto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/Lapiz.gif"))); // NOI18N
        botonPunto.setSelected(true);
        botonPunto.setToolTipText("Herramienta punto.");
        botonPunto.setFocusable(false);
        botonPunto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonPunto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPuntoActionPerformed(evt);
            }
        });
        panelFormas.add(botonPunto);

        grupoFormas.add(botonLinea);
        botonLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/Linea.gif"))); // NOI18N
        botonLinea.setToolTipText("Herramienta linea.");
        botonLinea.setFocusable(false);
        botonLinea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLinea.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLineaActionPerformed(evt);
            }
        });
        panelFormas.add(botonLinea);

        grupoFormas.add(botonRectangulo);
        botonRectangulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/Rectangulo.gif"))); // NOI18N
        botonRectangulo.setToolTipText("Herramienta rectángulo.");
        botonRectangulo.setFocusable(false);
        botonRectangulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRectangulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonRectangulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRectanguloActionPerformed(evt);
            }
        });
        panelFormas.add(botonRectangulo);

        grupoFormas.add(botonOvalo);
        botonOvalo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/Ovalo.gif"))); // NOI18N
        botonOvalo.setToolTipText("Herramienta elipse.");
        botonOvalo.setFocusable(false);
        botonOvalo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonOvalo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonOvalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOvaloActionPerformed(evt);
            }
        });
        panelFormas.add(botonOvalo);

        grupoFormas.add(botonCurva1pto);
        botonCurva1pto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/curva1pto.png"))); // NOI18N
        botonCurva1pto.setToolTipText("Herramienta Curva (1 pto control).");
        botonCurva1pto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCurva1ptoActionPerformed(evt);
            }
        });
        panelFormas.add(botonCurva1pto);

        toolBarDibujo.add(panelFormas);

        panelGrosor.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de linea / Grosor"));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0);
        flowLayout1.setAlignOnBaseline(true);
        panelGrosor.setLayout(flowLayout1);

        comboBoxStroke.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L. continua", ". . . . . . . . .", "- - - - - - ", "- . - . - . -", " " }));
        comboBoxStroke.setToolTipText("Tipo de linea de trazado");
        comboBoxStroke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxStrokeActionPerformed(evt);
            }
        });
        panelGrosor.add(comboBoxStroke);

        spinGrosor.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spinGrosor.setToolTipText("Grosor del trazo.");
        spinGrosor.setAutoscrolls(true);
        spinGrosor.setPreferredSize(new java.awt.Dimension(60, 28));
        spinGrosor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinGrosorStateChanged(evt);
            }
        });
        spinGrosor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinGrosorPropertyChange(evt);
            }
        });
        spinGrosor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spinGrosorKeyTyped(evt);
            }
        });
        panelGrosor.add(spinGrosor);

        toolBarDibujo.add(panelGrosor);

        panelColores.setBorder(javax.swing.BorderFactory.createTitledBorder("Color"));
        panelColores.setMinimumSize(new java.awt.Dimension(50, 150));
        panelColores.setLayout(new javax.swing.BoxLayout(panelColores, javax.swing.BoxLayout.Y_AXIS));

        panelFrenteFondo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        panelFrenteFondo.setPreferredSize(new java.awt.Dimension(150, 50));
        panelFrenteFondo.setLayout(new java.awt.GridLayout(1, 0, 0, 1));

        panelColorFrente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "C. Frente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 10))); // NOI18N
        panelColorFrente.setLayout(new java.awt.GridLayout(1, 0));
        panelFrenteFondo.add(panelColorFrente);

        panelColorFondo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "C. Fondo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 10))); // NOI18N
        panelColorFondo.setEnabled(false);
        panelColorFondo.setLayout(new java.awt.GridLayout(1, 0));
        panelFrenteFondo.add(panelColorFondo);

        panelColores.add(panelFrenteFondo);

        panelTodosColores.setPreferredSize(new java.awt.Dimension(50, 150));
        panelTodosColores.setLayout(new java.awt.GridLayout(0, 3, 0, 2));

        botonMasColores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pfinal/iconos/Cruz_Roja.png"))); // NOI18N
        botonMasColores.setToolTipText("Más colores.");
        botonMasColores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMasColoresActionPerformed(evt);
            }
        });
        panelTodosColores.add(botonMasColores);

        panelColores.add(panelTodosColores);

        toolBarDibujo.add(panelColores);

        panelRellenoEditar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelRellenoEditar.setLayout(new java.awt.GridLayout(0, 2));

        checkRelleno.setText("Relleno");
        checkRelleno.setToolTipText("Añadir relleno a la figura.");
        checkRelleno.setEnabled(false);
        checkRelleno.setPreferredSize(new java.awt.Dimension(82, 23));
        checkRelleno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRellenoActionPerformed(evt);
            }
        });
        panelRellenoEditar.add(checkRelleno);

        checkEditar.setText("Editar");
        checkEditar.setToolTipText("Editar la figura.");
        checkEditar.setPreferredSize(new java.awt.Dimension(82, 23));
        checkEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEditarActionPerformed(evt);
            }
        });
        panelRellenoEditar.add(checkEditar);

        labelTipoRelleno.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTipoRelleno.setText("Tipo relleno:");
        labelTipoRelleno.setEnabled(false);
        panelRellenoEditar.add(labelTipoRelleno);

        comboBoxTipoRelleno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Liso", "Vertical", "Horizontal", " " }));
        comboBoxTipoRelleno.setToolTipText("Tipo de relleno.");
        comboBoxTipoRelleno.setEnabled(false);
        comboBoxTipoRelleno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoRellenoActionPerformed(evt);
            }
        });
        panelRellenoEditar.add(comboBoxTipoRelleno);

        toolBarDibujo.add(panelRellenoEditar);

        panelPrincipal.add(toolBarDibujo, java.awt.BorderLayout.WEST);

        getContentPane().add(panelPrincipal, java.awt.BorderLayout.CENTER);

        menuArchivo.setText("Archivo");
        menuArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArchivoActionPerformed(evt);
            }
        });
        menuArchivo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                menuArchivoFocusGained(evt);
            }
        });

        menuNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuNuevo.setText("Nuevo");
        menuNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevoActionPerformed(evt);
            }
        });
        menuArchivo.add(menuNuevo);

        menuAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuAbrir.setText("Abrir");
        menuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuAbrir);

        menuGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        menuGuardar.setText("Guardar");
        menuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarActionPerformed(evt);
            }
        });
        menuArchivo.add(menuGuardar);

        barraMenu.add(menuArchivo);

        menuVer.setText("Ver");

        menuBarraEstado.setSelected(true);
        menuBarraEstado.setText("Barra de estado");
        menuBarraEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBarraEstadoActionPerformed(evt);
            }
        });
        menuVer.add(menuBarraEstado);

        menuBarraGeneral.setSelected(true);
        menuBarraGeneral.setText("Barra general");
        menuBarraGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBarraGeneralActionPerformed(evt);
            }
        });
        menuVer.add(menuBarraGeneral);

        menuBarraDibujo.setSelected(true);
        menuBarraDibujo.setText("Herramientas de dibujo");
        menuBarraDibujo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBarraDibujoActionPerformed(evt);
            }
        });
        menuVer.add(menuBarraDibujo);

        menuBarraImagen.setSelected(true);
        menuBarraImagen.setText("Herramientas de imagen");
        menuBarraImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBarraImagenActionPerformed(evt);
            }
        });
        menuVer.add(menuBarraImagen);

        barraMenu.add(menuVer);

        menuAyuda.setText("Ayuda");

        menuAcercaDe.setText("Acerca de...");
        menuAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAcercaDeActionPerformed(evt);
            }
        });
        menuAyuda.add(menuAcercaDe);

        barraMenu.add(menuAyuda);

        setJMenuBar(barraMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Asigna al lienzo la forma activa punto y deshabilita los atributos que no pertenecen
     * a la forma punto.
     * @param evt 
     */
    private void botonPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPuntoActionPerformed
        Lienzo.setFormaActiva("punto");
        labelHerramientaSelec.setText("Herramienta: punto");
        mostrarAtributosForma();
    }//GEN-LAST:event_botonPuntoActionPerformed

    /**
     * Lanza el dialogo de seleccion de tamaño de la nueva ventana de imagen.
     * @param evt 
     */
    private void menuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevoActionPerformed
        frameSizeChooser.setLocationRelativeTo(null);
        frameSizeChooser.setVisible(true);
    }//GEN-LAST:event_menuNuevoActionPerformed

    /**
     * Lanza el dialogo de guardado en caso de estar seleccionada en el escritorio
     * una ventana de imagen. Selecciona automaticamente la extensión y en caso de no
     * tenerla añade .jpg
     * @param evt 
     */
    private void menuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarActionPerformed
        if(escritorio.getSelectedFrame() instanceof VentanaImagen){
            JFileChooser dlg = new JFileChooser();
            FileFilter imageFilter = new FileNameExtensionFilter("Imagenes (.bmp, .jpg, .jpeg, .wbmp, .png, .gif)", ImageIO.getReaderFileSuffixes());
            dlg.addChoosableFileFilter(imageFilter);
            dlg.setAcceptAllFileFilterUsed(false);
            dlg.setFileFilter(imageFilter);
            int resp = dlg.showSaveDialog(this);
            if( resp == JFileChooser.APPROVE_OPTION) {
                try{
                      File f = dlg.getSelectedFile();
                      String path = f.getAbsolutePath();
                      String extension = null;
                     
                        int index = f.getAbsolutePath().lastIndexOf('.');
                        if (index != -1) {
                            extension = f.getAbsolutePath().substring(index + 1);
                        }else{
                            extension = "jpg";
                            f = new File(path + "." + extension);
                        }
                      
                      VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                      BufferedImage nueva = vi.getLienzo().guardar();
                      ImageIO.write(nueva, extension, f);
                     
                }catch(Exception ex){
                      JOptionPane.showMessageDialog(new JFrame(), "No se puede pudo guardar la imagen, comprueba que la extensión es correcta.", "Error al guardar", JOptionPane.ERROR_MESSAGE);
                }
             }
        }
    }//GEN-LAST:event_menuGuardarActionPerformed

    /**
     * Muestra u oculta la barra de estado.
     * @param evt 
     */
    private void menuBarraEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBarraEstadoActionPerformed

        if(!menuBarraEstado.isSelected()){
            labelHerramientaSelec.setVisible(false);
            menuBarraEstado.setSelected(false);
        }
        else{
            labelHerramientaSelec.setVisible(true);
            menuBarraEstado.setSelected(true);
        }
    }//GEN-LAST:event_menuBarraEstadoActionPerformed

    /**
     * Asigna al lienzo la forma activa linea y deshabilita los atributos que no pertenecen
     * a la forma linea.
     * @param evt 
     */
    private void botonLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLineaActionPerformed
        Lienzo.setFormaActiva("linea");
        labelHerramientaSelec.setText("Herramienta: linea");
        mostrarAtributosForma();
    }//GEN-LAST:event_botonLineaActionPerformed

    /**
     * Asigna al lienzo la forma activa rectangulo.
     * @param evt 
     */
    private void botonRectanguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRectanguloActionPerformed
        Lienzo.setFormaActiva("rectangulo");
        labelHerramientaSelec.setText("Herramienta: rectángulo");
        mostrarAtributosForma();
    }//GEN-LAST:event_botonRectanguloActionPerformed

    /**
     * Asigna al lienzo la forma activa elipse.
     * @param evt 
     */
    private void botonOvaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOvaloActionPerformed
        Lienzo.setFormaActiva("elipse");
        labelHerramientaSelec.setText("Herramienta: óvalo");
        mostrarAtributosForma();
    }//GEN-LAST:event_botonOvaloActionPerformed

    /**
     * Lanza el dialogo de apertura de archivos y abre los diferentes 
     * tipos de archivos (imagen, sonido, video) según el filtro seleccionado.
     * @param evt 
     */
    private void menuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirActionPerformed
        JFileChooser dlg = new JFileChooser();
        FileFilter imageFilter = new FileNameExtensionFilter("Imagenes (.bmp, .jpg, .jpeg, .wbmp, .png, .gif)", ImageIO.getReaderFileSuffixes());
        FileFilter soundFilter = new FileNameExtensionFilter("Sonido (.wave)", "wave");
        FileFilter videoFilter = new FileNameExtensionFilter("Video (.avi, .mpg, .mov)", "mpg", "avi", "mov");
        dlg.addChoosableFileFilter(imageFilter);
        dlg.addChoosableFileFilter(soundFilter);
        dlg.addChoosableFileFilter(videoFilter);
        dlg.setAcceptAllFileFilterUsed(false);
        dlg.setFileFilter(imageFilter);
        int resp = dlg.showOpenDialog(this);
        if( resp == JFileChooser.APPROVE_OPTION) {
              try{
                 
                 File f = dlg.getSelectedFile();
                 String filtro_select = dlg.getFileFilter().getDescription();

                 switch (filtro_select){
                     case "Imagenes (.bmp, .jpg, .jpeg, .wbmp, .png, .gif)":    
                        BufferedImage img = ImageIO.read(f);
                        VentanaImagen vi = new VentanaImagen(img.getHeight(), img.getWidth());
                        vi.setTitle(f.getName());
                        
                        vi.getLienzo().setImage(img);
                        this.escritorio.add(vi);
                        vi.setVisible(true);
                     break;
                     case "Sonido (.wave)":
                        VentanaReproduccion vr = new VentanaReproduccion(f);
                        vr.setTitle(f.getName());
                        this.escritorio.add(vr);
                        vr.setVisible(true);
                     break;
                     case "Video (.avi, .mpg, .mov)":
                        VentanaReproduccionJMF vj = new VentanaReproduccionJMF(f);
                        vj.setTitle(f.getName());
                        this.escritorio.add(vj);
                        vj.setVisible(true);
                     break;
                 }
              }catch(Exception ex){
                JOptionPane.showMessageDialog(new JFrame(), "Comprueba que el archivo contiene un formato de video, audio, imagen soportado.", "Error al abrir", JOptionPane.ERROR_MESSAGE);
              }
        }
    }//GEN-LAST:event_menuAbrirActionPerformed

    /**
     * Modifica el nivel de brillo de la imagen de la ventanaImagen seleccionada.
     * @param evt 
     */
    private void sliderBrilloStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderBrilloStateChanged
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {int posicion = sliderBrillo.getValue();
               if(imagen_a_mostrar!=null){
                 if(imagen_a_mostrar.getType()!=BufferedImage.TYPE_INT_RGB)
                     imagen_a_mostrar = convertImageType(imagen_a_mostrar,BufferedImage.TYPE_INT_RGB);
                 try{
                    RescaleOp rop = new RescaleOp(1.0F, (float)posicion, null);
                    BufferedImage imgdest = rop.filter(imagen_a_mostrar, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                    }
                } 
             }
    }//GEN-LAST:event_sliderBrilloStateChanged

    
    private void sliderBrilloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderBrilloFocusGained
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               imagen_a_mostrar = vi.getLienzo().getImage();
             }
    }//GEN-LAST:event_sliderBrilloFocusGained

    private void sliderBrilloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderBrilloFocusLost
        imagen_a_mostrar = null;
        sliderBrillo.setValue(0);
    }//GEN-LAST:event_sliderBrilloFocusLost

    /**
     * Aplica diferentes filtros a la imagen de la ventana seleccionada.
     * @param evt 
     */
    private void comboBoxFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxFiltroActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 try{
                    int indice = comboBoxFiltro.getSelectedIndex();
                    Kernel k = KernelProducer.createKernel(KernelProducer.TYPE_MEDIA_3x3); //DEFAULT
                    ConvolveOp cop = new ConvolveOp(k); //DEFAULT
                     
                    switch (indice){
                        case 0:
                            k = KernelProducer.createKernel(KernelProducer.TYPE_MEDIA_3x3);
                            cop = new ConvolveOp(k);
                            break;
                        case 1:
                            k = KernelProducer.createKernel(KernelProducer.TYPE_BINOMIAL_3x3);
                            cop = new ConvolveOp(k);
                            break;
                        case 2:
                            k = KernelProducer.createKernel(KernelProducer.TYPE_ENFOQUE_3x3);
                            cop = new ConvolveOp(k);
                            break;
                        case 3:
                            k = KernelProducer.createKernel(KernelProducer.TYPE_RELIEVE_3x3);
                            cop = new ConvolveOp(k);
                            break;
                        case 4:
                            k = KernelProducer.createKernel(KernelProducer.TYPE_LAPLACIANA_3x3);
                            cop = new ConvolveOp(k);
                            break;
                    }
         
                    BufferedImage imgdest = cop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_comboBoxFiltroActionPerformed

    /**
     * Habilita/deshabilita el modo edición de las formas (posición y atributos).
     * @param evt 
     */
    private void checkEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEditarActionPerformed
        if(checkEditar.isSelected()){
            Lienzo.setEditar(true);
            labelTipoRelleno.setEnabled(false);
            comboBoxTipoRelleno.setEnabled(false);
            Lienzo.setRelleno(false);
            Lienzo.setTipoRelleno(0);
            comboBoxTipoRelleno.setSelectedIndex(0);
        }else{
            Lienzo.setEditar(false);
            VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
            vi.getLienzo().repaint();
            if(checkRelleno.isSelected()){
                labelTipoRelleno.setEnabled(true);
                comboBoxTipoRelleno.setEnabled(true);
                Lienzo.setRelleno(true);
            }
        }
    }//GEN-LAST:event_checkEditarActionPerformed

    /**
     * Habilita/deshabilita el relleno de las formas y permite/impide seleccionar los diferentes tipos de relleno.
     * @param evt 
     */
    private void checkRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRellenoActionPerformed
        if (!Lienzo.getEditar()){
            if(checkRelleno.isSelected()){
                labelTipoRelleno.setEnabled(true);
                comboBoxTipoRelleno.setEnabled(true);
                Lienzo.setRelleno(true);
            }else{
                labelTipoRelleno.setEnabled(false);
                comboBoxTipoRelleno.setEnabled(false);
                Lienzo.setRelleno(false);
                Lienzo.setTipoRelleno(comboBoxTipoRelleno.getSelectedIndex());

            }
        }else if (Lienzo.getFormaAEditar() != null && Lienzo.getFormaAEditar() instanceof FormaRectangularMFG){
            if(checkRelleno.isSelected()){
                ((FormaRectangularMFG)Lienzo.getFormaAEditar()).setRelleno(true);
            }else{
                ((FormaRectangularMFG)Lienzo.getFormaAEditar()).setRelleno(false);
            }
        }
        VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
        vi.getLienzo().repaint();
    }//GEN-LAST:event_checkRellenoActionPerformed

    private void spinGrosorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spinGrosorKeyTyped

    }//GEN-LAST:event_spinGrosorKeyTyped

    private void spinGrosorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinGrosorPropertyChange

    }//GEN-LAST:event_spinGrosorPropertyChange

    /**
     * Cambia el grosor del trazo de la linea en las nuevas formas a dibujar.
     * @param evt 
     */
    private void spinGrosorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinGrosorStateChanged
        Float n = new Float(((Integer)spinGrosor.getValue()).floatValue());
        switch(comboBoxStroke.getSelectedIndex()){
            case 0:

                Lienzo.setStroke(new BasicStroke(n));
                if(Lienzo.getEditar() && Lienzo.getFormaAEditar() != null){
                    Lienzo.getFormaAEditar().setStroke(new BasicStroke(n));
                    VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                    vi.getLienzo().repaint();
                }
            break;
            case 1:
                float d1[]= {3.0f, 6.0f};
                Lienzo.setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d1, 0.0f));
                if(Lienzo.getEditar() && Lienzo.getFormaAEditar() != null){
                    Lienzo.getFormaAEditar().setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d1, 0.0f));
                    VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                    vi.getLienzo().repaint();
                }
            break;
            case 2:
                float d2[]= {10.0f};
                Lienzo.setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d2, 0.0f));
                if(Lienzo.getEditar() && Lienzo.getFormaAEditar() != null){
                    Lienzo.getFormaAEditar().setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d2, 0.0f));
                    VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                    vi.getLienzo().repaint();
                }
            break;
            case 3:
                float d3[]= {10.0f, 10.0f, 3.0f, 10.0f};
                Lienzo.setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d3, 0.0f));
                if(Lienzo.getEditar() && Lienzo.getFormaAEditar() != null){
                    Lienzo.getFormaAEditar().setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d3, 0.0f));
                    VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                    vi.getLienzo().repaint();
                }
            break;
        }
    }//GEN-LAST:event_spinGrosorStateChanged

    private void sliderGiroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderGiroFocusGained
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               imagen_a_mostrar = vi.getLienzo().getImage();
             }
    }//GEN-LAST:event_sliderGiroFocusGained

    private void sliderGiroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderGiroFocusLost
        imagen_a_mostrar = null;
        sliderGiro.setValue(0);
    }//GEN-LAST:event_sliderGiroFocusLost

    /**
     * Rota la imagen de la ventana seleccionada con respecto a su centro actual.
     * @param evt 
     */
    private void sliderGiroStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGiroStateChanged
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
                 int posicion = sliderGiro.getValue();
               if(imagen_a_mostrar!=null){
                 try{
                    double r = Math.toRadians(sliderGiro.getValue());
                    Point c = new Point(imagen_a_mostrar.getWidth()/2, imagen_a_mostrar.getHeight()/2); 
                    AffineTransform at = AffineTransform.getRotateInstance(r,c.x,c.y);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR); 
                    BufferedImage imgdest = atop.filter(imagen_a_mostrar, null);
                    
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                    
                    
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_sliderGiroStateChanged

    /**
     * Aumenta el contraste de la imagen de la ventana seleccionada.
     * @param evt 
     */
    private void botonContrasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonContrasteActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 if(imgSource.getType()!=BufferedImage.TYPE_INT_RGB)
                     imgSource = convertImageType(imgSource,BufferedImage.TYPE_INT_RGB);
                 try{
                    LookupTable lt; 
                    lt=LookupTableProducer.createLookupTable(LookupTableProducer.TYPE_SFUNCION); 
                    LookupOp ope = new LookupOp(lt, null);
                    BufferedImage imgdest = ope.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_botonContrasteActionPerformed

    /**
     * Oscurece la imagen de la ventana seleccionada.
     * @param evt 
     */
    private void botonOscurecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOscurecerActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 if(imgSource.getType()!=BufferedImage.TYPE_INT_RGB)
                     imgSource = convertImageType(imgSource,BufferedImage.TYPE_INT_RGB);
                 try{
                    LookupTable lt; 
                    lt=LookupTableProducer.createLookupTable(LookupTableProducer.TYPE_POWER); 
                    LookupOp ope = new LookupOp(lt, null);
                    BufferedImage imgdest = ope.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_botonOscurecerActionPerformed

    /**
     * Ilumina la imagen de la ventana seleccionada.
     * @param evt 
     */
    private void botonIluminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIluminarActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 if(imgSource.getType()!=BufferedImage.TYPE_INT_RGB)
                     imgSource = convertImageType(imgSource,BufferedImage.TYPE_INT_RGB);
                 try{
                    LookupTable lt; 
                    lt=LookupTableProducer.createLookupTable(LookupTableProducer.TYPE_LOGARITHM); 
                    LookupOp ope = new LookupOp(lt, null);
                    BufferedImage imgdest = ope.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_botonIluminarActionPerformed

    /**
     * Gira 90º a la derecha la imagen de la ventana seleccionada.
     * @param evt 
     */
    private void botonGiro90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGiro90ActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 try{
                    double r = Math.toRadians(90);
                    Point c = new Point(imgSource.getWidth()/2, imgSource.getHeight()/2); 
                    AffineTransform at = AffineTransform.getRotateInstance(r,c.x,c.y);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR); 
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_botonGiro90ActionPerformed

    /**
     * Gira 180º a la derecha la imagen de la ventana seleccionada.
     * @param evt 
     */
    private void botonGiro180ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGiro180ActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 try{
                    double r = Math.toRadians(180);
                    Point c = new Point(imgSource.getWidth()/2, imgSource.getHeight()/2); 
                    AffineTransform at = AffineTransform.getRotateInstance(r,c.x,c.y);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR); 
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_botonGiro180ActionPerformed

    /**
     * Gira 270º a la derecha la imagen de la ventana seleccionada.
     * @param evt 
     */
    private void botonGiro270ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGiro270ActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 try{
                    double r = Math.toRadians(270);
                    Point c = new Point(imgSource.getWidth()/2, imgSource.getHeight()/2); 
                    AffineTransform at = AffineTransform.getRotateInstance(r,c.x,c.y);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR); 
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_botonGiro270ActionPerformed

    /**
     * Aumenta el tamaño de la imagen de la ventana seleccionada en un 25%.
     * @param evt 
     */
    private void botonAumentarEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAumentarEscalaActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 try{
                    AffineTransform at = AffineTransform.getScaleInstance(1.25, 1.25);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR); 
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_botonAumentarEscalaActionPerformed

    /**
     * Disminuye el tamaño de la imagen de la ventana seleccionada en un 25%.
     * @param evt 
     */
    private void botonDisminuirEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDisminuirEscalaActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 try{
                    AffineTransform at = AffineTransform.getScaleInstance(0.75, 0.75);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR); 
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_botonDisminuirEscalaActionPerformed

    /**
     * Oculta/muestra la barra de herramientas de imagenes.
     * @param evt 
     */
    private void menuBarraImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBarraImagenActionPerformed
        if(!menuBarraImagen.isSelected()){
            toolBarImagen.setVisible(false);
            menuBarraImagen.setSelected(false);
        }
        else{
            toolBarImagen.setVisible(true);
            menuBarraImagen.setSelected(true);
        }
    }//GEN-LAST:event_menuBarraImagenActionPerformed

    /**
     * Oculta/Muestra la barra general.
     * @param evt 
     */
    private void menuBarraGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBarraGeneralActionPerformed
        if(!menuBarraGeneral.isSelected()){
            barraGeneral.setVisible(false);
            menuBarraGeneral.setSelected(false);
        }
        else{
            barraGeneral.setVisible(true);
            menuBarraGeneral.setSelected(true);
        }
    }//GEN-LAST:event_menuBarraGeneralActionPerformed

    /**
     * Oculta/Muestra la barra de herramientas de dibujo.
     * @param evt 
     */
    private void menuBarraDibujoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBarraDibujoActionPerformed
        if(!menuBarraDibujo.isSelected()){
            toolBarDibujo.setVisible(false);
            menuBarraDibujo.setSelected(false);
        }
        else{
            toolBarDibujo.setVisible(true);
            menuBarraDibujo.setSelected(true);
        }
    }//GEN-LAST:event_menuBarraDibujoActionPerformed

    /**
     * Muestra el dialogo de con la información de autor y versión.
     * @param evt 
     */
    private void menuAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAcercaDeActionPerformed
        
        dialogoAcercaDe.setVisible(true);
        dialogoAcercaDe.setLocationRelativeTo(null);

    }//GEN-LAST:event_menuAcercaDeActionPerformed

    /**
     * Muestra la ventana de selección de nuevos colores.
     * @param evt 
     */
    private void botonMasColoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMasColoresActionPerformed

        frameColorChooser.setLocationRelativeTo(this);
        frameColorChooser.setVisible(true);
    }//GEN-LAST:event_botonMasColoresActionPerformed

    /**
     * Coloca el nuevo color seleccionado como color de trazo o relleno según corresponda
     * y oculta la ventana de selección de colores.
     * @param evt 
     */
    private void botonAñadirColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAñadirColorActionPerformed
        Color c = colorChooser.getColor();
        if(boton_color_frente.isSelected()){
            boton_color_frente.setColor(c);
            Lienzo.setColorTrazo(c);
        }else if(boton_color_fondo.isSelected()){
            boton_color_fondo.setColor(c);
            Lienzo.setColorFondo(c);
        }
        
        frameColorChooser.setVisible(false);
    }//GEN-LAST:event_botonAñadirColorActionPerformed

    /**
     * Muestra la ventana de grabación de audio.
     * @param evt 
     */
    private void botonGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGrabarActionPerformed
        vg.setVisible(true);
        botonGuardar.setEnabled(false);
        menuGuardar.setEnabled(false);
    }//GEN-LAST:event_botonGrabarActionPerformed

    private void textFieldAlturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldAlturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldAlturaActionPerformed

    /**
     * Crea la nueva ventana de imagen con el tamaño introducido y oculta la ventana de selección de tamaño.
     * @param evt 
     */
    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        int an = Integer.parseInt(textFieldAnchura.getText());
        int al = Integer.parseInt(textFieldAltura.getText());
        VentanaImagen vi = new VentanaImagen(al, an);
        escritorio.add(vi);
        vi.setVisible(true);
        frameSizeChooser.setVisible(false);
        botonGuardar.setEnabled(true);
        menuGuardar.setEnabled(true);
    }//GEN-LAST:event_botonAceptarActionPerformed

    /**
     * Lanza la ventana de selección de tamaño para la nueva ventana de imagen.
     * @param evt 
     */
    private void botonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoActionPerformed
        frameSizeChooser.setLocationRelativeTo(null);
        frameSizeChooser.setVisible(true);
    }//GEN-LAST:event_botonNuevoActionPerformed

    /**
     * Establece el tipo de linea de trazo a usar en las nuevas formas a dibujar.
     * @param evt 
     */
    private void comboBoxStrokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxStrokeActionPerformed
        Float n = new Float(((Integer)spinGrosor.getValue()).floatValue());
        switch(comboBoxStroke.getSelectedIndex()){
            case 0:

                Lienzo.setStroke(new BasicStroke(n));
                if(Lienzo.getEditar() && Lienzo.getFormaAEditar() != null){
                    Lienzo.getFormaAEditar().setStroke(new BasicStroke(n));
                    VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                    vi.getLienzo().repaint();
                }
            break;
            case 1:
                float d1[]= {3.0f, 6.0f};
                Lienzo.setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d1, 0.0f));
                if(Lienzo.getEditar() && Lienzo.getFormaAEditar() != null){
                    Lienzo.getFormaAEditar().setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d1, 0.0f));
                    VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                    vi.getLienzo().repaint();
                }
            break;
            case 2:
                float d2[]= {10.0f};
                Lienzo.setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d2, 0.0f));
                if(Lienzo.getEditar() && Lienzo.getFormaAEditar() != null){
                    Lienzo.getFormaAEditar().setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d2, 0.0f));
                    VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                    vi.getLienzo().repaint();
                }
            break;
            case 3:
                float d3[]= {10.0f, 10.0f, 3.0f, 10.0f};
                Lienzo.setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d3, 0.0f));
                if(Lienzo.getEditar() && Lienzo.getFormaAEditar() != null){
                    Lienzo.getFormaAEditar().setStroke(new BasicStroke(n, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, d3, 0.0f));
                    VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                    vi.getLienzo().repaint();
                }
            break;
        }
    }//GEN-LAST:event_comboBoxStrokeActionPerformed

 
    private void menuArchivoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_menuArchivoFocusGained
        
    }//GEN-LAST:event_menuArchivoFocusGained
    private void menuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArchivoActionPerformed

    }//GEN-LAST:event_menuArchivoActionPerformed

    /**
     * Muestra la ventana de la webcam.
     * @param evt 
     */
    private void botonWebcamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonWebcamActionPerformed
        if(vc == null){
            vc = new VentanaCamara();
            if (vc.getPlayer() != null){
                escritorio.add(vc);
                System.out.println(vc);
            }else{
                vc = null;
            }
        }else{
            vc.setVisible(true);
        }
    }//GEN-LAST:event_botonWebcamActionPerformed

    /**
     * Crea una nueva ventana de imagen en el escritorio con una captura del video 
     * que se estaba reporduciendo o de la webcam si estaba abierta su ventana.
     * @param evt 
     */
    private void botonCapturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCapturarActionPerformed

        JInternalFrame v_selec = escritorio.getSelectedFrame();
        if (v_selec instanceof VentanaReproduccionJMF){
            Player p = ((VentanaReproduccionJMF)v_selec).getPlayer();
            FrameGrabbingControl fgc;
            String claseCtr = "javax.media.control.FrameGrabbingControl";
            fgc = (FrameGrabbingControl)p.getControl(claseCtr);
            Buffer bufferFrame = fgc.grabFrame();
            BufferToImage bti;
            bti = new BufferToImage((VideoFormat)bufferFrame.getFormat());
            BufferedImage i = (BufferedImage)bti.createImage(bufferFrame);

            VentanaImagen vi = new VentanaImagen(i.getHeight(), i.getWidth());
            vi.setTitle("Captura");
            
            vi.getLienzo().setImage(i);
            this.escritorio.add(vi);
            vi.setVisible(true);
        }
    }//GEN-LAST:event_botonCapturarActionPerformed

    /**
     * Crea una nueva ventana de imagen copia exacta de la actual ventana de imagen seleccionada.
     * @param evt 
     */
    private void botonDuplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDuplicarActionPerformed
       if(escritorio.getSelectedFrame() instanceof VentanaImagen){
            VentanaImagen s = (VentanaImagen)escritorio.getSelectedFrame();
            VentanaImagen n = new VentanaImagen(s.getLienzo().getImage().getHeight(), s.getLienzo().getImage().getWidth());
            n.setTitle("Copia de " + s.getTitle());
            
            n.getLienzo().setImage(s.getLienzo().getImage());
            this.escritorio.add(n);
            n.setVisible(true);
        }
    }//GEN-LAST:event_botonDuplicarActionPerformed

    /**
     * Pone en negativo la imagen de la ventana seleccionada.
     * @param evt 
     */
    private void botonNegativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNegativoActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 if(imgSource.getType()!=BufferedImage.TYPE_INT_RGB)
                     imgSource = convertImageType(imgSource,BufferedImage.TYPE_INT_RGB);
                 try{
                    LookupTable lt; 
                    lt=LookupTableProducer.createLookupTable(LookupTableProducer.TYPE_NEGATIVE); 
                    LookupOp ope = new LookupOp(lt, null);
                    BufferedImage imgdest = ope.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_botonNegativoActionPerformed

    /**
     * Lanza el dialogo de apertura de archivos y abre los diferentes 
     * tipos de archivos (imagen, sonido, video) según el filtro seleccionado.
     * @param evt 
     */
    private void botonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrirActionPerformed
        JFileChooser dlg = new JFileChooser();
        FileFilter imageFilter = new FileNameExtensionFilter("Imagenes (.bmp, .jpg, .jpeg, .wbmp, .png, .gif)", ImageIO.getReaderFileSuffixes());
        FileFilter soundFilter = new FileNameExtensionFilter("Sonido (.wave)", "wave");
        FileFilter videoFilter = new FileNameExtensionFilter("Video (.avi, .mpg, .mov)", "mpg", "avi", "mov");
        dlg.addChoosableFileFilter(imageFilter);
        dlg.addChoosableFileFilter(soundFilter);
        dlg.addChoosableFileFilter(videoFilter);
        dlg.setAcceptAllFileFilterUsed(false);
        dlg.setFileFilter(imageFilter);
        int resp = dlg.showOpenDialog(this);
        if( resp == JFileChooser.APPROVE_OPTION) {
              try{
                 
                 File f = dlg.getSelectedFile();
                 String filtro_select = dlg.getFileFilter().getDescription();

                 switch (filtro_select){
                     case "Imagenes (.bmp, .jpg, .jpeg, .wbmp, .png, .gif)":    
                        BufferedImage img = ImageIO.read(f);
                        VentanaImagen vi = new VentanaImagen(img.getHeight(), img.getWidth());
                        vi.setTitle(f.getName());
                        
                        vi.getLienzo().setImage(img);
                        this.escritorio.add(vi);
                        vi.setVisible(true);
                        botonGuardar.setEnabled(true);
                        menuGuardar.setEnabled(true);
                     break;
                     case "Sonido (.wave)":
                        VentanaReproduccion vr = new VentanaReproduccion(f);
                        vr.setTitle(f.getName());
                        this.escritorio.add(vr);
                        vr.setVisible(true);
                        botonGuardar.setEnabled(false);
                        menuGuardar.setEnabled(false);
                     break;
                     case "Video (.avi, .mpg, .mov)":
                        VentanaReproduccionJMF vj = new VentanaReproduccionJMF(f);
                        vj.setTitle(f.getName());
                        this.escritorio.add(vj);
                        vj.setVisible(true);
                        botonGuardar.setEnabled(false);
                        menuGuardar.setEnabled(false);
                     break;
                 }
              }catch(Exception ex){
                JOptionPane.showMessageDialog(new JFrame(), "Comprueba que el archivo contiene un formato de video, audio, imagen soportado.", "Error al abrir", JOptionPane.ERROR_MESSAGE);
              }
        }
    }//GEN-LAST:event_botonAbrirActionPerformed

    /**
     * Lanza el dialogo de guardado en caso de estar seleccionada en el escritorio
     * una ventana de imagen. Selecciona automaticamente la extensión y en caso de no
     * tenerla añade .jpg
     * @param evt 
     */
    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        if(escritorio.getSelectedFrame() instanceof VentanaImagen){
            JFileChooser dlg = new JFileChooser();
            FileFilter imageFilter = new FileNameExtensionFilter("Imagenes (.bmp, .jpg, .jpeg, .wbmp, .png, .gif)", ImageIO.getReaderFileSuffixes());
            dlg.addChoosableFileFilter(imageFilter);
            dlg.setAcceptAllFileFilterUsed(false);
            dlg.setFileFilter(imageFilter);
            int resp = dlg.showSaveDialog(this);
            if( resp == JFileChooser.APPROVE_OPTION) {
                try{
                      File f = dlg.getSelectedFile();
                      String path = f.getAbsolutePath();
                      String extension = null;
                     
                        int index = f.getAbsolutePath().lastIndexOf('.');
                        if (index != -1) {
                            extension = f.getAbsolutePath().substring(index + 1);
                        }else{
                            extension = "jpg";
                            f = new File(path + "." + extension);
                        }
                      
                      VentanaImagen vi = (VentanaImagen)escritorio.getSelectedFrame();
                      BufferedImage nueva = vi.getLienzo().guardar();
                      ImageIO.write(nueva, extension, f);
                     
                }catch(Exception ex){
                      JOptionPane.showMessageDialog(new JFrame(), "No se puede pudo guardar la imagen, comprueba que la extensión es correcta.", "Error al guardar", JOptionPane.ERROR_MESSAGE);
                }
             }
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    /**
     * Asigna al lienzo la forma activa curva1pto y deshabilita los atributos que no pertenecen
     * a la forma curva1pto.
     * @param evt 
     */
    private void botonCurva1ptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCurva1ptoActionPerformed
        Lienzo.setFormaActiva("curva1pto");
        labelHerramientaSelec.setText("Herramienta: curva 1 con un punto de control");
        mostrarAtributosForma();
    }//GEN-LAST:event_botonCurva1ptoActionPerformed

    /**
     * Asigna al lienzo el tipo de relleno seleccionado.
     * @param evt 
     */
    private void comboBoxTipoRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoRellenoActionPerformed
        switch(comboBoxTipoRelleno.getSelectedIndex()){
            case 0:
                Lienzo.setTipoRelleno(0);
            break;
            case 1:
                Lienzo.setTipoRelleno(1);
            break;
            case 2:
                Lienzo.setTipoRelleno(2);
            break;

        }
    }//GEN-LAST:event_comboBoxTipoRellenoActionPerformed

    /**
     * Actualiza el en el lienzo el color de frente al mismo del boton de color de frente.
     * @param evt 
     */
    private void escritorioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_escritorioFocusLost
        Lienzo.setColorTrazo(boton_color_frente.getColor());
    }//GEN-LAST:event_escritorioFocusLost

    /**
     * Pone la imagen de la ventana seleccionada en escala de grises.
     * @param evt 
     */
    private void botonEscalaGrisesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEscalaGrisesActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 if(imgSource.getType()!=BufferedImage.TYPE_INT_RGB)
                     imgSource = convertImageType(imgSource,BufferedImage.TYPE_INT_RGB);
                 try{
                    ICC_Profile ip;
                    ip = ICC_Profile.getInstance(ColorSpace.CS_GRAY);
                    ColorSpace cs = new ICC_ColorSpace(ip);
                    ColorConvertOp ccop = new ColorConvertOp(cs, null);
                    BufferedImage imgdest = ccop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_botonEscalaGrisesActionPerformed

    private void sliderUmbralFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderUmbralFocusGained
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               imagen_a_mostrar = vi.getLienzo().getImage();
             }
    }//GEN-LAST:event_sliderUmbralFocusGained

    private void sliderUmbralFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderUmbralFocusLost
        imagen_a_mostrar = null;
        sliderUmbral.setValue(0);
    }//GEN-LAST:event_sliderUmbralFocusLost

    /**
     * Modifica el umbral de la imagen de la ventana seleccionada.
     * @param evt 
     */
    private void sliderUmbralStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderUmbralStateChanged
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {int posicion = sliderUmbral.getValue();
               if(imagen_a_mostrar!=null){
                 if(imagen_a_mostrar.getType()!=BufferedImage.TYPE_INT_RGB)
                     imagen_a_mostrar = convertImageType(imagen_a_mostrar,BufferedImage.TYPE_INT_RGB);
                 try{
                    ThresholdOp thop = new ThresholdOp(ThresholdOp.TYPE_GREY_LEVEL);
                    //thop.useOriginalColor(true);
                    thop.setThreshold(posicion);
                    BufferedImage imgdest = thop.filter(imagen_a_mostrar, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                    }
                } 
             }
    }//GEN-LAST:event_sliderUmbralStateChanged

    /**
     * Operación propia. Cambia las bandas de color según lo seleccionado.
     * @param evt 
     */
    private void comboBoxBandSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxBandSelectorActionPerformed
        VentanaImagen vi = (VentanaImagen) (escritorio.getSelectedFrame());
             if (vi != null) {
               BufferedImage imgSource = vi.getLienzo().getImage();
               if(imgSource!=null){
                 if(imgSource.getType()!=BufferedImage.TYPE_INT_RGB)
                     imgSource = convertImageType(imgSource,BufferedImage.TYPE_INT_RGB);
                 try{
                    BandSelectorOp bsop = new BandSelectorOp(true, false, false);
                    switch (comboBoxBandSelector.getSelectedIndex()){
                        case 0:
                            bsop = new BandSelectorOp(true, false, false);
                        break;
                        case 1:
                            bsop = new BandSelectorOp(false, true, false);
                        break;
                        case 2:
                            bsop = new BandSelectorOp(false, false, true);
                        break;
                        case 3:
                            bsop = new BandSelectorOp(true, true, false);
                        break;
                        case 4:
                            bsop = new BandSelectorOp(true, false, true);
                        break;
                        case 5:
                            bsop = new BandSelectorOp(false, true, true);
                        break;
                    }
                    BufferedImage imgdest = bsop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                 } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                 }
                }
             }
    }//GEN-LAST:event_comboBoxBandSelectorActionPerformed

    /**
     * Deshabilita/habilita el guardado si no está seleccionada una ventana de imagen.
     * @param evt 
     */
    private void escritorioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_escritorioMouseExited
        if(escritorio.getSelectedFrame() instanceof VentanaReproduccion || escritorio.getSelectedFrame() instanceof VentanaGrabacion || escritorio.getSelectedFrame() instanceof VentanaReproduccionJMF){
            menuGuardar.setEnabled(false);
            botonGuardar.setEnabled(false);
            repaint();
        }else{
            menuGuardar.setEnabled(true);
            botonGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_escritorioMouseExited

    /**
     * Deshabilita/habilita el guardado si no está seleccionada una ventana de imagen.
     * @param evt 
     */
    private void escritorioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_escritorioMouseEntered
       if(escritorio.getSelectedFrame() instanceof VentanaReproduccion || escritorio.getSelectedFrame() instanceof VentanaGrabacion || escritorio.getSelectedFrame() instanceof VentanaReproduccionJMF){
            menuGuardar.setEnabled(false);
            botonGuardar.setEnabled(false);
            repaint();
        }else{
            menuGuardar.setEnabled(true);
            botonGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_escritorioMouseEntered


    /**
     * Habilita/deshabilita diferentes atributos de dibujo dependiendo de la forma activa en ese momento.
     */
    public void mostrarAtributosForma(){
        String f = Lienzo.getFormaActiva();
        
        if(f.equals("punto") || f.equals("linea") || f.equals("curva1pto")){
            panelColorFondo.setEnabled(false);
            boton_color_fondo.setEnabled(false);
            checkRelleno.setEnabled(false);    
        }else{
            panelColorFondo.setEnabled(true);
            boton_color_fondo.setEnabled(true);
            checkRelleno.setEnabled(true);
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraGeneral;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton botonAbrir;
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonAumentarEscala;
    private javax.swing.JButton botonAñadirColor;
    private javax.swing.JButton botonCapturar;
    private javax.swing.JButton botonContraste;
    private javax.swing.JToggleButton botonCurva1pto;
    private javax.swing.JButton botonDisminuirEscala;
    private javax.swing.JButton botonDuplicar;
    private javax.swing.JButton botonEscalaGrises;
    private javax.swing.JButton botonGiro180;
    private javax.swing.JButton botonGiro270;
    private javax.swing.JButton botonGiro90;
    private javax.swing.JButton botonGrabar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonIluminar;
    private javax.swing.JToggleButton botonLinea;
    private javax.swing.JButton botonMasColores;
    private javax.swing.JButton botonNegativo;
    private javax.swing.JButton botonNuevo;
    private javax.swing.JButton botonOscurecer;
    private javax.swing.JToggleButton botonOvalo;
    private javax.swing.JToggleButton botonPunto;
    private javax.swing.JToggleButton botonRectangulo;
    private javax.swing.JButton botonWebcam;
    private javax.swing.JCheckBox checkEditar;
    private javax.swing.JCheckBox checkRelleno;
    private javax.swing.JColorChooser colorChooser;
    private javax.swing.JComboBox comboBoxBandSelector;
    private javax.swing.JComboBox comboBoxFiltro;
    private javax.swing.JComboBox comboBoxStroke;
    private javax.swing.JComboBox comboBoxTipoRelleno;
    private javax.swing.JDialog dialogoAcercaDe;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JFrame frameColorChooser;
    private javax.swing.JFrame frameSizeChooser;
    private javax.swing.ButtonGroup grupoFormas;
    private javax.swing.ButtonGroup grupoFrenteFondo;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JLabel labelAltura;
    private javax.swing.JLabel labelAnchura;
    private javax.swing.JLabel labelHerramientaSelec;
    private javax.swing.JLabel labelTexto;
    private javax.swing.JLabel labelTipoRelleno;
    private pfinal.Lienzo lienzo1;
    private javax.swing.JMenuItem menuAbrir;
    private javax.swing.JMenuItem menuAcercaDe;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JCheckBoxMenuItem menuBarraDibujo;
    private javax.swing.JCheckBoxMenuItem menuBarraEstado;
    private javax.swing.JCheckBoxMenuItem menuBarraGeneral;
    private javax.swing.JCheckBoxMenuItem menuBarraImagen;
    private javax.swing.JMenuItem menuGuardar;
    private javax.swing.JMenuItem menuNuevo;
    private javax.swing.JMenu menuVer;
    private javax.swing.JPanel panelBrillo;
    private javax.swing.JPanel panelColorFondo;
    private javax.swing.JPanel panelColorFrente;
    private javax.swing.JPanel panelColores;
    private javax.swing.JPanel panelContraste;
    private javax.swing.JPanel panelDuplicar;
    private javax.swing.JPanel panelEscala;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JPanel panelFormas;
    private javax.swing.JPanel panelFrenteFondo;
    private javax.swing.JPanel panelGrosor;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelRellenoEditar;
    private javax.swing.JPanel panelRotacion;
    private javax.swing.JPanel panelTodo;
    private javax.swing.JPanel panelTodosColores;
    private javax.swing.JPanel panelUmbral;
    private javax.swing.JSlider sliderBrillo;
    private javax.swing.JSlider sliderGiro;
    private javax.swing.JSlider sliderUmbral;
    private javax.swing.JSpinner spinGrosor;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTextField textFieldAltura;
    private javax.swing.JTextField textFieldAnchura;
    private javax.swing.JToolBar toolBarDibujo;
    private javax.swing.JToolBar toolBarImagen;
    // End of variables declaration//GEN-END:variables
}
