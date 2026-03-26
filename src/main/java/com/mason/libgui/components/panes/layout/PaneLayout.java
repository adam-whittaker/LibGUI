package com.mason.libgui.components.panes.layout;

import com.mason.libgui.utils.structures.*;
import com.mason.libgui.utils.structures.interfaces.RectQuery;

import static java.lang.Math.max;

public class PaneLayout{


    private final Box rootBox;
    private final BoxAddressManager boxAddressManager;


    public PaneLayout(RectQuery bounds){
        rootBox = new Box(bounds);
        boxAddressManager = new BoxAddressManager(rootBox);
    }


    public Coord centre(SizedIdentifiable identifiable){
        return centre(identifiable.getName(), identifiable.getSize());
    }

    public Coord centre(String address, Size size){
        Box box = findBox(address);
        box.verifyEmpty();
        return box.centreSizeInBox(size);
    }

    public RectQuery getBounds(String address){
        Box box = findBox(address);
        box.verifyEmpty();
        return box;
    }

    private Box findBox(String address){
        if(boxAddressManager.isNamedAddress(address)){
            return boxAddressManager.findBoxFromNamedAddress(address);
        }
        if(boxAddressManager.startsWithNamedAddress(address)){
            return findBoxFromAddressWithNamedStart(address);
        }
        return findBoxFromUnnamedAddress(address, rootBox);
    }


    private Box findBoxFromAddressWithNamedStart(String address){
        Box startBox = boxAddressManager.findInitialBoxFromNamedStartAddress(address);
        String addressTail = boxAddressManager.getAddressTail(address);
        return findBoxFromUnnamedAddress(addressTail, startBox);
    }

    private Box findBoxFromUnnamedAddress(String address, Box initialBox){
        String[] subAddresses = boxAddressManager.getSubAddresses(address);
        Box currentBox = initialBox;
        for(String subAddress : subAddresses){
            currentBox = currentBox.getSubBoxAtAddress(subAddress);
        }
        return currentBox;
    }

    public void nameAddress(String address, String name){
        boxAddressManager.nameBox(findBox(address), name);
    }


    public void divide(String address, int rows, int columns){
        divideBox(findBox(address), rows, columns);
    }

    private void divideBox(Box box, int rows, int columns){
        box.verifyEmpty();
        int x = box.x();
        int y = box.y();
        int w = box.width()/columns;
        int h = box.height()/rows;
        for(int row=0; row<rows; row++){
            for(int col=0; col<columns; col++){
                box.putSubBox(coordToSubAddress(row, col), new Rect(x+col*w, y+row*h, w, h));
            }
        }
    }

    private String coordToSubAddress(int row, int col){
        return "["+ row + "," + col + "]";
    }


    public void verticalDissect(String address, double[] dissectionRatios){
        Box box = findBox(address);
        box.verifyEmpty();
        int x = box.x();
        int currentY = box.y();
        int totalWidth = box.width();
        int totalHeight = box.height();
        int currentHeight;

        for(int n=0; n<dissectionRatios.length; n++){
            currentHeight = (int)(totalHeight * dissectionRatios[n]);
            box.putSubBox(coordToSubAddress(n, 0), new Rect(x, currentY, totalWidth, currentHeight));
            currentY += currentHeight;
        }
        currentHeight = box.y() + totalHeight - currentY;
        box.putSubBox(coordToSubAddress(dissectionRatios.length, 0), new Rect(x, currentY, totalWidth, currentHeight));
    }

    public void horizontalDissect(String address, double[] dissectionRatios){
        Box box = findBox(address);
        box.verifyEmpty();
        int currentX = box.x();
        int y = box.y();
        int totalWidth = box.width();
        int totalHeight = box.height();
        int currentWidth;

        for(int n=0; n<dissectionRatios.length; n++){
            currentWidth = (int)(totalWidth * dissectionRatios[n]);
            box.putSubBox(coordToSubAddress(0, n), new Rect(currentX, y, currentWidth, totalHeight));
            currentX += currentWidth;
        }
        currentWidth = box.x() + totalWidth - currentX;
        box.putSubBox(coordToSubAddress(0, dissectionRatios.length), new Rect(currentX, y, currentWidth, totalHeight));
    }


    public void reduceBoxSizeWithMargin(String address, double horizontalMarginRatio, double verticalMarginRatio){
        if(max(horizontalMarginRatio, verticalMarginRatio) >= 0.5){
            throw new IllegalArgumentException("Margin ratio too large");
        }
        Box box = findBox(address);
        box.verifyEmpty();
        int horMargin = (int)(horizontalMarginRatio * box.width());
        int vertMargin = (int)(verticalMarginRatio * box.height());
        box.setBounds(new Rect(
                box.x() + horMargin,
                box.y() + vertMargin,
                box.width() - 2*horMargin,
                box.height() - 2*vertMargin));
    }

    public void setAbsoluteBoxWidth(String address, int width){
        Box box = findBox(address);
        box.verifyEmpty();
        if(width > box.width()){
            throw new IllegalStateException("Box cannot contain requested width.");
        }
        box.setBounds(new Rect(
                box.x() + width/2,
                box.y(),
                width,
                box.height()
        ));
    }

    public void setAbsoluteBoxHeight(String address, int height){
        Box box = findBox(address);
        box.verifyEmpty();
        if(height > box.height()){
            throw new IllegalStateException("Box cannot contain requested height.");
        }
        box.setBounds(new Rect(
                box.x(),
                box.y() + height/2,
                box.width(),
                height
        ));
    }

}
