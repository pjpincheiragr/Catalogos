package aplicacion.herramientas.java;
import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.awt.event.*;
import javax.swing.text.*;
import java.util.Date;

public class DateField extends JTextField implements KeyListener, FocusListener{

    char[] mask;
    SimpleDateFormat format;
   
    public DateField(String format){
            //este lanza una excepcion si esta mal formateada
            this.format = new SimpleDateFormat(format);
            mask = new char[format.length()];
   
            char[] arr = format.toCharArray();
            for(int i=0; i<arr.length; i++){
                   
                    if(Character.isLetter(arr[i])){
                            mask[i] += '#';
                    } else if(arr[i] == '/' || arr[i] == '-' || arr[i] == ' ' || arr[i] == ':'){
                            mask[i] += arr[i];
                    } else{
                           // throw new InvalidParamException("Invalid character: " + arr[i]);
                    }
            }
           
            setDocument(new MyDocument(mask));
            //agregar listeners
            addKeyListener(this);
            addFocusListener(this);
            setDate(null);
    }
   
    public void keyPressed(KeyEvent e) {
            //evitar copiay y pegar en la caja de texto
            if(e.isControlDown()){
                    if(e.getKeyCode() == KeyEvent.VK_X
                            || e.getKeyCode() == KeyEvent.VK_C
                            || e.getKeyCode() == KeyEvent.VK_V){
                            e.consume();
                    }
            }
    }

    public void keyTyped(KeyEvent e) {
            //solo permitimos numeros
            if(e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9){
                    e.consume(); return;
            }
           
            //si el caret está en la ultima posición, no se hace nada (no se ingresa en la caja)
            int currentCaretPos = getCaretPosition();
            if(currentCaretPos >= mask.length){
                    e.consume(); return;
            }
           
            //si el usuario puso el carte antes de un caracter de separacion, lo brincamos
            if(mask[currentCaretPos] != '#'){
                    setCaretPosition(currentCaretPos + 1);
                    e.consume(); return;
            }
    }
   
    public void keyReleased(KeyEvent e) {
            //cuando se presionan las flechas, el evento se retorna
            //y se regresa el caret
            if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT){
                    return;
            }
           
            int caretPosition = getCaretPosition();
           
            if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && caretPosition > 0){
                    setCaretPosition(caretPosition - 1);
                    e.consume();
                    return;
            }
           
            if(caretPosition < mask.length){
                    char m = mask[caretPosition];
                    if(m  == '/' || m == '-' || m == ' ' || m == ':'){
                            setCaretPosition(getCaretPosition() + 1);
                    }
            }
    }
   
    public void focusGained(FocusEvent e) { }

    public void focusLost(FocusEvent e) {
            try{
                    format.parse(super.getText());
            } catch(ParseException pe){
                    setDate(null);
            }
    }
   
    public void setDate(Date date){
            if(date == null){
                    super.setText(new String(mask).replace('#', ' '));
                    setCaretPosition(0);
            } else{
                    super.setText(format.format(date));
            }
    }
   
    public Date getDate(){
            try {
                    return format.parse(super.getText());
            } catch (ParseException e) { }
            return null;
    }
   
    private class MyDocument extends PlainDocument{
            char[] mask;
           
            public MyDocument(char[] mask){
                    this.mask = mask;
            }
           
            public void insertString(int offs, String str, AttributeSet a)
                            throws BadLocationException {
                   
                    //la única vez que va a ser 0 es en el setText inicial, por lo que no se reemplaza nada
                    //solo se inserta, de ahi pal real el textField siempre tendra texto
                    if(getLength() > 0){
                            super.remove(offs, str.length());
                    }
                    super.insertString(offs, str, a);
            }

            public void remove(int offs, int len) throws BadLocationException {
                   
                    //solo se elimina cuando es una tecla, y se reemplaza por su respectiva
                    if(len == 1){
                            char c = mask[offs];
                           
                            if (c == '#') c= ' ';
                           
                            insertString(offs, "" + c, null);
                    }
            }
    }
   /*
    //override getText and setText
    @Override public void setText(String text){
            throw new OperationNotAllowed("setText is deprecated, use setDate instead");
    }
   
    @Override public String getText(){
            throw new OperationNotAllowed("getText is deprecated, use getDate instead");
    }*/
}
