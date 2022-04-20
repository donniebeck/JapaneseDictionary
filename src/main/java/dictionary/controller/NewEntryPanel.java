package dictionary.controller;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dictionary.entity.FullEntry;
import dictionary.entity.Keyword;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class NewEntryPanel extends JPanel
{
	/**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 723256576376043971L;
	private JTextField kanjiField;
	private JTextField readingField;
	private JTextField definitionField;
	private FullEntry newEntry;
	private JComboBox<Keyword> verbComboBox;
	private JComboBox<Keyword> posComboBox;
	/**
	 * Create the panel.
	 * @param dictionary 
	 */
	public NewEntryPanel()
	{
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		setFont(new Font("Meiryo", Font.PLAIN, 15));
		
		JLabel kanjiLabel = new JLabel("Kanji: ");
		add(kanjiLabel, "2, 2, right, default");
		
		kanjiField = new JTextField();
		add(kanjiField, "4, 2, fill, default");
		kanjiField.setColumns(10);
		
		JLabel readingLabel = new JLabel("Reading: ");
		add(readingLabel, "2, 4, right, default");
		
		readingField = new JTextField();
		add(readingField, "4, 4, fill, default");
		readingField.setColumns(10);
		
		JLabel definitionLabel = new JLabel("Definition: ");
		add(definitionLabel, "2, 6, right, default");
		
		definitionField = new JTextField();
		add(definitionField, "4, 6, fill, default");
		definitionField.setColumns(10);
		
		JButton confirmBtn = new JButton("confirm");
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parseFields();
			}
		});
		
		Vector<Keyword> posModel = new Vector<Keyword>();
		posModel.add(new Keyword(17)); //noun
		posModel.add(new Keyword(68)); //verb
		posModel.add(new Keyword(1)); //i-adj
		posModel.add(new Keyword(2)); //na-adj
		posModel.add(new Keyword(3)); //no-adj
		posModel.add(new Keyword(7)); //ii-adjective
		posModel.add(new Keyword(6)); //adverb
		posModel.add(new Keyword(13)); //expression
		posModel.add(new Keyword(14)); //interjection
		posModel.add(new Keyword(67)); //proper noun
		posModel.add(new Keyword(98)); //unclassified

		
		
		JLabel posLabel = new JLabel("Part of Speech: ");
		add(posLabel, "2, 8, right, default");
		posComboBox = new JComboBox<Keyword>(posModel);
		posComboBox.setFont(new Font("Meiryo", Font.PLAIN, 15));
		add(posComboBox, "4, 8, fill, default");
		posComboBox.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		       if(((Keyword) posComboBox.getSelectedItem()).isVerb())
		       {
		    	   verbComboBox.setVisible(true);
		       }
		       else
		       {
		    	   verbComboBox.setVisible(false);
		       }
		    }
		});
		
		Vector<Keyword> verbModel = new Vector<Keyword>();
		verbModel.add(new Keyword(28)); //ru
		verbModel.add(new Keyword(41)); //u
		verbModel.add(new Keyword(40)); //tsu
		verbModel.add(new Keyword(37)); //uru
		verbModel.add(new Keyword(35)); //mu
		verbModel.add(new Keyword(31)); //bu
		verbModel.add(new Keyword(36)); //nu
		verbModel.add(new Keyword(33)); //ku
		verbModel.add(new Keyword(32)); //gu
		verbModel.add(new Keyword(39)); //su
		verbModel.add(new Keyword(34)); //iku
		verbModel.add(new Keyword(47)); //suru
		verbModel.add(new Keyword(45)); //kuru
		

		verbComboBox = new JComboBox<Keyword>(verbModel);
		verbComboBox.setFont(new Font("Meiryo", Font.PLAIN, 15));
		verbComboBox.setVisible(false);
		add(verbComboBox, "4, 10, fill, default");
		add(confirmBtn, "2, 20, 3, 1");
		
		

	}

	public void parseFields()
	{
		if (!(kanjiField.getText().isEmpty() && readingField.getText().isEmpty() && definitionField.getText().isEmpty()))
		{
			newEntry = new FullEntry(DictionaryWindow.dictionary.getNewID());
			newEntry.addKanji(kanjiField.getText());
			newEntry.addReading(readingField.getText());
			newEntry.addDefinition(definitionField.getText());
			if(((Keyword) posComboBox.getSelectedItem()).isVerb())
			{
				newEntry.addPartOfSpeechTagID(((Keyword) verbComboBox.getSelectedItem()).getId());
			} else
			{
				newEntry.addPartOfSpeechTagID(((Keyword) posComboBox.getSelectedItem()).getId());
			}
			
			DictionaryWindow.dictionary.insertEntry(newEntry);
			DictionaryWindow.closeNewEntryFrame();
		} else
		{
			JFrame jFrame = new JFrame();
	        JOptionPane.showMessageDialog(jFrame, "Please enter values into every field");
		}

	}

}
