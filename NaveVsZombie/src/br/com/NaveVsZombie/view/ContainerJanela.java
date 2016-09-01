/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombie.view;

import br.com.NaveVsZombie.model.Comum;
import javax.swing.JFrame;

/**
 *
 * @author OCTI01
 */
public class ContainerJanela extends JFrame implements Comum {

    public ContainerJanela() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Jogo de nave");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(larguraTela + 5, alturaTela + 25);
        add(new Fase());
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new ContainerJanela().setVisible(true);
    }
}
