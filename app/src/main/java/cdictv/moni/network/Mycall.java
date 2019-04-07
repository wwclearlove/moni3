package cdictv.moni.network;

import java.io.IOException;

public interface Mycall {
    public void success(String json);
    public void faild(IOException e);
}
