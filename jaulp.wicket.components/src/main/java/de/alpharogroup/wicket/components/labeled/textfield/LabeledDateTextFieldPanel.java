/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.labeled.textfield;

import java.util.Date;
import java.util.Locale;

import lombok.Getter;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled DateTextfield.
 *
 * @param <T>
 *            the generic type
 */
public class LabeledDateTextFieldPanel<T> extends LabeledFormComponentPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The text field. */
	@Getter
	private final DateTextField dateTextField;

	/**
	 * Instantiates a new LabeledDateTextfieldPanel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledDateTextFieldPanel(final String id, final IModel<T> model,
		final IModel<String> labelModel)
	{
		super(id, model, labelModel);
		add(dateTextField = newDateTextField("dateTextField", model));

		add(feedback = newComponentFeedbackPanel("feedback", dateTextField));

		final String markupId = dateTextField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void convertInput()
	{
		setConvertedInput(getModel().getObject());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInput()
	{
		return dateTextField.getInput();
	}

	/**
	 * Factory method for creating the DateTextField. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a
	 * DateTextField.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text field
	 */
	protected DateTextField newDateTextField(final String id, final IModel<T> model)
	{
		final IModel<Date> textFieldModel = new PropertyModel<>(model.getObject(), getId());

		final DateTextField dateTextField = new DateTextField(id, textFieldModel,
			new StyleDateConverter("S-", true))
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Locale getLocale()
			{
				return getSession().getLocale();
			}
		};
		final DatePicker datePicker = new DatePicker()
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean enableMonthYearSelection()
			{
				return true;
			}
		};
		datePicker.setShowOnFieldClick(true);
		dateTextField.add(datePicker);
		dateTextField.setOutputMarkupId(true);
		return dateTextField;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onBeforeRender()
	{
		dateTextField.setRequired(isRequired());
		super.onBeforeRender();
	}
}
