package calculator_client;

import java.awt.*;

/**
 * @author Bogdan Kovalev.
 */
public class GBC extends GridBagConstraints {
    public GBC x_y(int gridx, int gridy) {
        this.gridx = gridx;
        this.gridy = gridy;
        return this;
    }

    public GBC widthHeight(int gridwidth, int gridheigth) {
        this.gridwidth = gridwidth;
        this.gridheight = gridheigth;
        return this;
    }

    public GBC weight(double weightx, double weighty) {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    public GBC anchor(int anchor) {
        this.anchor = anchor;
        return this;
    }

    public GBC fill(int fill) {
        this.fill = fill;
        return this;
    }

    public GBC insets(Insets insets) {
        this.insets = insets;
        return this;
    }

    public GBC setIpads(int ipadx, int ipady) {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}
