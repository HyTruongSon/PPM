// Author: Hy Truong Son
// Major: BSc. Computer Science
// Class: 2013 - 2016
// Institution: Eotvos Lorand University, Budapest, Hungary
// Email: sonpascal93@gmail.com
// Website: http://people.inf.elte.hu/hytruongson/
// Copyright 2016 (c) Hy Truong Son. All rights reserved. Use for academic purposes only!

package viewer;

public class MainProgram {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                new FileSelectionFrame();
            }
        }.start();
    }
    
}