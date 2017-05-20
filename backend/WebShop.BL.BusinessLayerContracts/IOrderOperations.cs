using System.Collections.Generic;

using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.BL.BusinessLayerContracts
{
    public interface IOrderOperations
    {
        void Create(OrderDTO order);
        IEnumerable<OrderDTO> Get();
    }
}
