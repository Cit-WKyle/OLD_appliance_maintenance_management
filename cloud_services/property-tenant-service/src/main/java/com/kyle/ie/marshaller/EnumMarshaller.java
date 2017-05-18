package com.kyle.ie.marshaller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;

public class EnumMarshaller implements DynamoDBMarshaller<Enum> {

	@Override
	public String marshall(Enum arg0) {
		return arg0.name();
	}

	@Override
	public Enum unmarshall(Class<Enum> arg0, String arg1) {
		return Enum.valueOf(arg0, arg1);
	}

}
