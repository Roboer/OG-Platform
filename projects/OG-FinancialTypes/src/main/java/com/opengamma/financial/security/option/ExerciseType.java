/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.option;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

import com.opengamma.util.ArgumentChecker;

/**
 * The type of exercise in an option.
 */
@BeanDefinition
public abstract class ExerciseType extends DirectBean implements Serializable {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;
  /**
   * Cache of known types.
   */
  private static volatile ConcurrentMap<String, ExerciseType> s_cache;

  private static void registerKnownTypes() {
    synchronized (ExerciseType.class) {
      if (s_cache == null) {
        s_cache = new ConcurrentHashMap<>(8, 0.75f, 1);
        register(new AmericanExerciseType());
        register(new AsianExerciseType());
        register(new BermudanExerciseType());
        register(new EuropeanExerciseType());
      }
    }
  }
  
  /**
   * Gets an exercise type by name.
   * 
   * @param name  the name to find, not null
   * @return the exercise type, not null
   */
  @FromString
  public static ExerciseType of(String name) {
    ArgumentChecker.notNull(name, "name");
   
    registerKnownTypes();
    ExerciseType type = s_cache.get(name);
    if (type == null) {
      throw new IllegalArgumentException("Unknown ExerciseType: " + name);
    }
    return type;
  }

  /**
   * Registers an exercise type.
   * 
   * @param type  the exercise type, not null
   */
  public static void register(ExerciseType type) {
    ArgumentChecker.notNull(type, "type");
    
    registerKnownTypes();
    s_cache.putIfAbsent(type.getName(), type);
  }
  

  //-------------------------------------------------------------------------
  /**
   * Creates an instance.
   */
  protected ExerciseType() {
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the exercise type.
   * 
   * @return the exercise type, not null
   */
  @ToString
  public abstract String getName();

  //-------------------------------------------------------------------------
  /**
   * Accepts a visitor to manage traversal of the hierarchy.
   * 
   * @param <T> the result type of the visitor
   * @param visitor  the visitor, not null
   * @return the result
   */
  public abstract <T> T accept(ExerciseTypeVisitor<T> visitor);

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExerciseType}.
   * @return the meta-bean, not null
   */
  public static ExerciseType.Meta meta() {
    return ExerciseType.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ExerciseType.Meta.INSTANCE);
  }

  @Override
  public ExerciseType.Meta metaBean() {
    return ExerciseType.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExerciseType}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null);

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends ExerciseType> builder() {
      throw new UnsupportedOperationException("ExerciseType is an abstract class");
    }

    @Override
    public Class<? extends ExerciseType> beanType() {
      return ExerciseType.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
