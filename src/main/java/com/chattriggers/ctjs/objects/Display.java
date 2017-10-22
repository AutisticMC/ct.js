package com.chattriggers.ctjs.objects;

import com.chattriggers.ctjs.CTJS;
import com.chattriggers.ctjs.libs.ChatLib;
import com.chattriggers.ctjs.utils.console.Console;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import java.util.ArrayList;
import java.util.Collections;

public class Display {
    private ArrayList<String> lines;
    @Getter @Setter
    private float renderX;
    @Getter @Setter
    private float renderY;
    @Getter @Setter
    private boolean shouldRender;
    @Getter
    private DisplayHandler.Background background;
    @Getter
    private int backgroundColor;
    @Getter
    private DisplayHandler.Align align;
    @Getter
    private int textColor;
    @Getter
    private DisplayHandler.Order order;

    private FontRenderer ren = Minecraft.getMinecraft().fontRenderer;

    public Display() {
        lines = new ArrayList<>();

        //TODO: Default render X & Y?
        renderX = 0;
        renderY = 0;

        shouldRender = false;

        background = DisplayHandler.Background.NONE;
        backgroundColor = 0x50000000;
        align = DisplayHandler.Align.LEFT;
        order = DisplayHandler.Order.DOWN;

        textColor = 0xffffffff;

        CTJS.getInstance().getDisplayHandler().registerDisplay(this);
    }


    /**
     * Sets a display's text color.
     * @param color the integer color of the text
     * @return the display to allow for method chaining
     */
    public Display setTextColor(int color) {
        this.textColor = color;
        return this;
    }



    /**
     * Sets a display's background type.
     * @param background the type of background
     * @return the display to allow for method chaining
     */
    public Display setBackground(DisplayHandler.Background background) {
        this.background = background;
        return this;
    }

    /**
     * Sets a display's background type.
     * Background types are: FULL, PER_LINE
     * @param background
     * @return
     */
    public Display setBackground(String background) {
        switch (background.toUpperCase()) {
            case("FULL"):
                this.background = DisplayHandler.Background.FULL;
                break;
            case("PER_LINE"):
            case("PER LINE"):
                this.background = DisplayHandler.Background.PER_LINE;
                break;
        }
        return this;
    }

    /**
     * Sets a display's background color.
     * @param color the integer color of the background
     * @return the display to allow for method chaining
     */
    public Display setBackgroundColor(int color) {
        this.backgroundColor = color;
        return this;
    }


    
    /**
     * Sets a display's text alignment.
     * @param align the type of alignment
     * @return the display to allow for method chaining
     */
    public Display setAlign(DisplayHandler.Align align) {
        this.align = align;
        return this;
    }

    public Display setAlign(String align) {
        switch (align.toUpperCase()) {
            case("LEFT"):
                this.align = DisplayHandler.Align.LEFT;
                break;
            case("RIGHT"):
                this.align = DisplayHandler.Align.RIGHT;
                break;
            case("CENTER"):
                this.align = DisplayHandler.Align.CENTER;
                break;
        }
        return this;
    }



    /**
     * Sets a display's line order.
     * @param order the order of lines
     * @return the display to allow method chaining
     */
    public Display setOrder(DisplayHandler.Order order) {
        this.order = order;
        return this;
    }

    public Display setOrder(String order) {
        switch (order.toUpperCase()) {
            case("DOWN"):
                this.order = DisplayHandler.Order.DOWN;
                break;
            case("UP"):
                this.order = DisplayHandler.Order.UP;
                break;
        }
        return this;
    }



    /**
     * Sets a display line to a certain string.
     * @param lineNumber the line number to set (0 based)
     * @param line the string to set the line to
     * @return the display to allow for method chaining
     */
    public Display setLine(int lineNumber, String line) {
        try {
            lines.set(lineNumber, ChatLib.addColor(line));
        } catch (Exception exception) {
            Console.getConsole().printStackTrace(exception);
        }
        return this;
    }

    /**
     * Gets a string from a line in a display.
     * @param lineNumber the line number to get
     * @return the string in line of display
     */
    public String getLine(int lineNumber) {
        try {
            return lines.get(lineNumber);
        } catch (Exception exception) {
            return "null";
        }
    }

    /**
     * Adds as many lines as you specify onto the end of
     * the display (appends them).
     * @param lines a variable amount of lines to add
     * @return the display to allow for method chaining
     */
    public Display addLines(String... lines) {
        for (int i = 0; i < lines.length; i++) {
            lines[i] = ChatLib.addColor(lines[i]);
        }

        Collections.addAll(this.lines, lines);
        return this;
    }

    /**
     * Adds one line to a display.
     * @param line the line to add
     * @return the display to allow for method chaining
     */
    public Display addLine(String line) {
        return addLines(line);
    }

    /**
     * Adds a number of empty lines to a display.
     * @param lines the number of lines to add
     * @return the display to allow for method chaining
     */
    public Display addLines(int lines) {
        for (int i=0; i<lines; i++) {
            this.lines.add("");
        }
        return this;
    }

    /**
     * Clears all the lines in the display.
     * @return the display to allow for method chaining
     */
    public Display clearLines() {
        lines.clear();
        return this;
    }



    /**
     * Set the X and Y render position of the display.
     * @param renderX the x coordinate
     * @param renderY the y coordinate
     * @return the display to allow for method chaining
     */
    public Display setRenderLoc(int renderX, int renderY) {
        this.renderX = renderX;
        this.renderY = renderY;
        return this;
    }



    // Renders the display on to the player's screen.
    void render() {
        if (!shouldRender) return;

        if (this.background == DisplayHandler.Background.FULL) {
            int maxWidth = 0;
            for (String line : lines) {
                if (ren.getStringWidth(line) > maxWidth)
                    maxWidth = ren.getStringWidth(line);
            }

            if (this.order == DisplayHandler.Order.DOWN)
                drawBackground(this.renderX, this.renderY, maxWidth, lines.size()*10);
            else if (this.order == DisplayHandler.Order.UP)
                drawBackground(this.renderX, this.renderY + 10, maxWidth, -lines.size()*10);
        }

        int i = 0;

        for (String line : lines) {
            if (!line.equals("") && this.background == DisplayHandler.Background.PER_LINE)
                drawBackground(this.renderX, this.renderY + (i*10), ren.getStringWidth(line), 10);

            drawString(line, this.renderX, this.renderY + (i * 10));

            if (order == DisplayHandler.Order.DOWN)
                i++;
            else if (order == DisplayHandler.Order.UP)
                i--;
        }
    }

    // helper method to draw background with align
    private void drawBackground(float x, float y, float width, float height) {
        if (this.align == DisplayHandler.Align.LEFT)
            drawRect(x, y, x + width, y + height, this.backgroundColor);
        if (this.align == DisplayHandler.Align.RIGHT)
            drawRect(x - width, y, x, y + height, this.backgroundColor);
        if (this.align == DisplayHandler.Align.CENTER)
            drawRect(x - width/2, y, x + width/2, y + height, this.backgroundColor);
    }

    // helper method to draw string with align
    private void drawString(String line, float x, float y) {
        if (this.align == DisplayHandler.Align.LEFT)
            ren.drawStringWithShadow(line, x, y, this.textColor);
        else if (this.align == DisplayHandler.Align.RIGHT)
            ren.drawStringWithShadow(line, x - ren.getStringWidth(line), y, this.textColor);
        else if (this.align == DisplayHandler.Align.CENTER)
            ren.drawStringWithShadow(line, x - ren.getStringWidth(line)/2, y, this.textColor);
    }

    // TODO: move somewhere to actually be used in js
    private void drawRect(float left, float top, float right, float bottom, int color) {
        if (left < right) {
            float i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            float j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left+1, bottom-1, 0.0D).endVertex();
        worldrenderer.pos(right-2, bottom-1, 0.0D).endVertex();
        worldrenderer.pos(right-2, top-1, 0.0D).endVertex();
        worldrenderer.pos(left+1, top-1, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
