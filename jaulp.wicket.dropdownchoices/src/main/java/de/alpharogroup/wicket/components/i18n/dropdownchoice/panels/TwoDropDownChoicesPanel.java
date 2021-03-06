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
package de.alpharogroup.wicket.components.i18n.dropdownchoice.panels;

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import java.util.List;

import lombok.Getter;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesModel;

/**
 * The Class TwoDropDownChoicesPanel contains two dropdowns with a root and a child dropdown.
 *
 * @author Asterios Raptis
 */
public abstract class TwoDropDownChoicesPanel<T> extends BasePanel<TwoDropDownChoicesModel<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	public static final String ROOT_CHOICE_ID = "rootChoice";
	public static final String CHILD_CHOICE_ID = "childChoice";

	/** The root choice. */
	@Getter
	private DropDownChoice<T> rootChoice;

	/** The child choice. */
	@Getter
	private DropDownChoice<T> childChoice;

	/**
	 * Instantiates a new two drop down choices panel.
	 *
	 * @param id
	 *            the id
	 * @param stringTwoDropDownChoicesModel
	 *            the string two drop down choices model
	 * @param rootRenderer
	 *            the root renderer
	 * @param childRenderer
	 *            the child renderer
	 */
	public TwoDropDownChoicesPanel(final String id,
		final IModel<TwoDropDownChoicesModel<T>> stringTwoDropDownChoicesModel,
		final IChoiceRenderer<T> rootRenderer, final IChoiceRenderer<T> childRenderer)
	{
		super(id, stringTwoDropDownChoicesModel);
		IModel<T> selectedRootOptionModel = null;
		getModelObject().getRootChoices();
		selectedRootOptionModel = model(from(getModel()).getSelectedRootOption());
		// selectedRootOptionModel = new PropertyModel<T>(stringTwoDropDownChoicesModel,
		// "selectedRootOption");
		rootChoice = newRootChoice(ROOT_CHOICE_ID, selectedRootOptionModel, getModelObject()
			.getRootChoices(), rootRenderer);
		IModel<T> selectedChildOptionModel = null;
		selectedChildOptionModel = model(from(getModel()).getSelectedChildOption());
		// selectedChildOptionModel = new PropertyModel<T>(stringTwoDropDownChoicesModel,
		// "selectedChildOption");
		childChoice = newChildChoice(CHILD_CHOICE_ID, selectedChildOptionModel, getModelObject()
			.getChildChoices(), childRenderer);

		add(rootChoice);
		add(childChoice);
	}

	/**
	 * Factory method for create a new child choice. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a new
	 * child choice.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            the choices
	 * @param renderer
	 *            the renderer
	 * @return the child choice
	 */
	protected DropDownChoice<T> newChildChoice(final String id, final IModel<T> model,
		final IModel<? extends List<? extends T>> choices, final IChoiceRenderer<? super T> renderer)
	{
		final DropDownChoice<T> cc = new LocalisedDropDownChoice<>(id, model, choices, renderer);
		cc.setOutputMarkupId(true);
		return cc;
	}

	/**
	 * Factory method for create a new root choice. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a new
	 * root choice.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            the choices
	 * @param renderer
	 *            the renderer
	 * @return the root choice
	 */
	protected DropDownChoice<T> newRootChoice(final String id, final IModel<T> model,
		final IModel<? extends List<? extends T>> choices, final IChoiceRenderer<? super T> renderer)
	{
		final DropDownChoice<T> rc = new LocalisedDropDownChoice<>(id, model, choices, renderer);
		rc.add(new AjaxFormComponentUpdatingBehavior("onchange")
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				target.add(childChoice);
			}
		});
		return rc;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
	}

}
