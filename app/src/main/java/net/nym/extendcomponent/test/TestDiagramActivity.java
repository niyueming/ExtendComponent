package net.nym.extendcomponent.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import net.nym.extendcomponent.R;
import net.nym.extendcomponent.widget.DiagramView;

public class TestDiagramActivity extends ActionBarActivity {

    DiagramView diagramView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_diagram);
        diagramView = (DiagramView) findViewById(R.id.diagramView);
        diagramView.test();
	}

	
}
